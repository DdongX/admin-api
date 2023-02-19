package pers.ddongx.admin.common.security;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pers.ddongx.admin.common.constant.JwtConstant;
import pers.ddongx.admin.domain.CheckResult;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.exception.BusinessException;
import pers.ddongx.admin.service.IUserService;
import pers.ddongx.admin.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Description: Jwt请求过滤器
 *
 * @author DdongX
 * @since 2023/2/12 17:16
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    @Autowired
    private IUserService userService;
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    private static final String JWT_HEADER_TOKEN = "token";

    private static final String[] URL_WHITELIST = {"/login", "/logout", "/captcha", "/password", "/image/**", "/test/**"};

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(JWT_HEADER_TOKEN);
        if (StrUtil.isBlank(token) && Arrays.asList(URL_WHITELIST).contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }
        CheckResult checkResult = JwtUtil.validateJwt(token);
        if (!checkResult.getSuccess()) {
            switch (checkResult.getErrorCode()) {
                case JwtConstant.JWT_ERROR_CODE_NULL:
                    throw new BusinessException("Token不存在");
                case JwtConstant.JWT_ERROR_CODE_FAIL:
                    throw new BusinessException("Token验证不通过");
                case JwtConstant.JWT_ERROR_CODE_EXPIRE:
                    throw new BusinessException("Token已过期");
                default:
                    throw new BusinessException("认证失败，请联系管理员");
            }
        }
        Claims claims = JwtUtil.parseJwt(token);
        String userName = claims.getSubject();
        User user = userService.getByUserName(userName);

        UsernamePasswordAuthenticationToken usernameAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null, myUserDetailsServiceImpl.getUserAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernameAuthenticationToken);
        chain.doFilter(request, response);
    }
}
