package pers.ddongx.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Description: SpringSecurity配置类
 *
 * @author DdongX
 * @since 2023/1/2 15:41
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] URL_WHITELIST = {"/login", "/logout", "/captcha", "/password", "/image/**", "/test/**"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域支持并且关闭csrf攻击
        http.cors().and().csrf().disable()

                // 登陆登出配置
                .formLogin()
//                .successHandler()
//                .failureHandler().and().logout().logoutSuccessHandler()

                // 禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 拦截规则配置
                .and()
                .authorizeRequests().antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated();

        // 异常处理
        // 自定义过滤器配置
    }
}
