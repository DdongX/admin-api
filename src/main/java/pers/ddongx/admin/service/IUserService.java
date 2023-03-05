package pers.ddongx.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ddongx.admin.domain.entity.User;

/**
 * description 针对表【user】的数据库操作Service
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名获取用户详细相信
     *
     * @param userName 用户名
     * @return 用户详情
     */
    User getByUserName(String userName);

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    String getUserAuthorityInfo(Long userId);
}
