package pers.ddongx.admin.common.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pers.ddongx.admin.domain.Result;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 重写登陆成功处理器
 *
 * @author DdongX
 * @since 2023/2/11 19:21
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String message = e.getMessage();
        if (e instanceof BadCredentialsException) {
            message = "用户名或密码错误";
        }

        Result result = new Result();
        result.setCode("401");
        result.setData(null);
        result.setTimestamp(System.currentTimeMillis());
        result.setUri(httpServletRequest.getRequestURI());
        result.setMessage(message);

        outputStream.write(JSON.toJSONString(result).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
