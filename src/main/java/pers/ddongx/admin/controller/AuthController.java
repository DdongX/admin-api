package pers.ddongx.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ddongx.admin.service.IUserService;

/**
 * Description: 登陆认证相关
 *
 * @author DdongX
 * @since 2023/5/3 15:40
 */
@Tag(name = "登陆认证")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @SaIgnore
    @Operation(summary = "登陆接口")
    @GetMapping("/login/{username}/{password}")
    public String login(@Parameter(description = "用户名/手机号/邮箱") @PathVariable String username, @Parameter(description = "密码(加密后)") @PathVariable String password) {
        return userService.login(username, password);
    }
}
