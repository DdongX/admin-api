package pers.ddongx.admin.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.UserPO;
import pers.ddongx.admin.exception.BusinessException;
import pers.ddongx.admin.mapper.UserMapper;
import pers.ddongx.admin.service.IUserService;
import pers.ddongx.admin.util.AESUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现类
 *
 * @author DdongX
 * @since 2023-05-01
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements IUserService {

    public final RedisTemplate redisTemplate;

    /**
     * 用户登陆失败次数
     */
    private static final String FAIL_COUNT_REDIS_KEY = "login_fail_count";
    /**
     * 用户锁定
     */
    private static final String LOCK_REDIS_KEY = "login_lock";
    /**
     * 分隔符
     */
    private static final String SEPARATOR = ":";

    @Override
    public String login(String username, String password) {
        // 根据用户名、手机号、邮箱查询是否存在，如果不存在，则提示账号不存在
        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPO::getLoginName, username).or().eq(UserPO::getEmail, username).or().eq(UserPO::getMobile, AESUtil.encrypt(username));
        Assert.isTrue(count(wrapper) > 0, "用户不存在");
        // 先查询用户
        UserPO po = getOne(wrapper);
        // 查询是否被锁定
        Assert.isFalse(isForbidden(po.getLoginName()), "用户被锁定，请30分钟后再试");
        // 如果未被锁定，则判断密码是否正确
        if (password.equals(AESUtil.encrypt(po.getPassword()))) {
            setFailCounter(po.getLoginName());
            throw new BusinessException("密码错误，请检查后重试");
        }
        // 登陆成功,删除计数器
        deleteFilCounter(po.getLoginName());
        // 生成token
        StpUtil.login(po.getId());
        // 设置session
        SaSession session = StpUtil.getSession();
        session.set("login_name", po.getLoginName());
        session.set("nickname", po.getNickname());
        return StpUtil.getTokenValue();
    }

    /**
     * 用户是否被锁定
     *
     * @param loginName 登录名
     * @return 成功
     */
    private boolean isForbidden(String loginName) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(String.join(SEPARATOR, FAIL_COUNT_REDIS_KEY, loginName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 登陆失败，次数+1
     *
     * @param loginName 登录名
     */
    private void setFailCounter(String loginName) {

        // 登录失败次数 + 1
        String key = String.join(SEPARATOR, FAIL_COUNT_REDIS_KEY, loginName, String.valueOf(DateUtil.thisMinute()));
        Integer count = (Integer) redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().increment(key, 1); // 如果key不存在的话就会以增量形式存储进来

        if (count == null) {
            redisTemplate.expire(key, 10, TimeUnit.MINUTES);
        }

        // 如果失败次数大于5次，锁定账户
        List<String> windowsKeys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            windowsKeys.add(String.join(SEPARATOR, FAIL_COUNT_REDIS_KEY, loginName, String.valueOf(DateUtil.thisMinute())));
        }
        List<Integer> countList = redisTemplate.opsForValue().multiGet(windowsKeys);

        assert countList != null;

        int total = 0;
        for (Integer c : countList) {
            total += c;
        }
        if (total >= 5) {
            forbidden(loginName);
        }
    }

    /**
     * 锁定账户
     *
     * @param loginName 登录名
     */
    private void forbidden(String loginName) {
        String key = String.join(SEPARATOR, FAIL_COUNT_REDIS_KEY, loginName);
        redisTemplate.opsForValue().set(key, 1, 30, TimeUnit.MINUTES);
    }

    /**
     * 登陆成功，删除计数器
     *
     * @param loginName 登录名
     */
    private void deleteFilCounter(String loginName) {
        List<String> windowKeys = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            windowKeys.add(String.join(SEPARATOR, FAIL_COUNT_REDIS_KEY, loginName, String.valueOf(DateUtil.thisMinute())));
        }
        redisTemplate.delete(windowKeys);
    }
}
