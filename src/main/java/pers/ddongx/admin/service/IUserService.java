package pers.ddongx.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ddongx.admin.domain.entity.UserPO;

/**
 * 用户服务类
 *
 * @author DdongX
 * @since 2023-05-01
 */
public interface IUserService extends IService<UserPO> {
    /**
     * 登陆接口
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);
}
