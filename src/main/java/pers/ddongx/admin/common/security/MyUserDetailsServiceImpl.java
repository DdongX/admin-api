package pers.ddongx.admin.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.exception.AdminExceptionCode;
import pers.ddongx.admin.exception.BaseException;
import pers.ddongx.admin.service.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: 用户详情
 *
 * @author DdongX
 * @since 2023/2/11 23:23
 */
@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUserName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        } else if ("1".equals(user.getStatus())) {
            throw new BaseException(AdminExceptionCode.ACCOUNT_LOCK);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getUserAuthorities());
    }

    public List<GrantedAuthority> getUserAuthorities() {
        return new ArrayList<>();
    }
}
