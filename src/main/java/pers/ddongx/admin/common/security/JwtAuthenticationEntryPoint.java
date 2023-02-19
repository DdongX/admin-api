package pers.ddongx.admin.common.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pers.ddongx.admin.domain.Result;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: Jwt认证异常处理器
 *
 * @author DdongX
 * @since 2023/2/12 18:21
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        Result result = new Result();
        result.setCode("401");
        result.setData(null);
        result.setTimestamp(System.currentTimeMillis());
        result.setUri(httpServletRequest.getRequestURI());
        result.setMessage("认证失败，请登录！");

        outputStream.write(JSON.toJSONString(result).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
