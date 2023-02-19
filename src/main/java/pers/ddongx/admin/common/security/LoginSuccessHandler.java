package pers.ddongx.admin.common.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pers.ddongx.admin.domain.Result;
import pers.ddongx.admin.util.JwtUtil;

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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String userName = authentication.getName();
        String token = JwtUtil.generateJwtToken(userName);

        Result result = new Result();
        result.setCode("200");
        result.setData(token);
        result.setTimestamp(System.currentTimeMillis());
        result.setUri(httpServletRequest.getRequestURI());
        result.setMessage("登陆成功");

        outputStream.write(JSON.toJSONString(result).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
