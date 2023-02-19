package pers.ddongx.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.mapper.UserMapper;
import pers.ddongx.admin.service.IUserService;

/**
 * description 针对表【user】的数据库操作Service实现
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements IUserService {

    @Override
    public User getByUserName(String userName) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getName, userName));
    }
}




