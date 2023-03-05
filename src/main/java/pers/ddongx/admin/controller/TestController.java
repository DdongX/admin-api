package pers.ddongx.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.service.IUserService;
import pers.ddongx.admin.util.JwtUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试
 *
 * @author Ddong
 * @since 2022/12/4
 */
@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private IUserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping("/user/list")
    public List<User> test() {
        return userService.list();
    }

    @GetMapping("/login")
    public String login() {
        return JwtUtil.generateJwtToken("DdongX");
    }
}
