package pers.ddongx.admin.common.security;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pers.ddongx.admin.domain.R;
import pers.ddongx.admin.domain.entity.Menu;
import pers.ddongx.admin.domain.entity.Role;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.domain.vo.MenuVO;
import pers.ddongx.admin.service.IMenuService;
import pers.ddongx.admin.service.IRoleService;
import pers.ddongx.admin.service.IUserService;
import pers.ddongx.admin.util.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 重写登陆成功处理器
 *
 * @author DdongX
 * @since 2023/2/11 19:21
 */
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final IUserService userService;
    private final IRoleService roleService;
    private final IMenuService menuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        String userName = authentication.getName();
        String token = JwtUtil.generateJwtToken(userName);

        User currentUser = userService.getByUserName(userName);

        // 根据用户ID获取所有角色信息
        List<Role> roleList = roleService.list(new LambdaQueryWrapper<Role>().inSql(Role::getId, "SELECT role_id FROM user_role WHERE user_id = " + currentUser.getId()));

        Set<Menu> menuSet = new HashSet<>();
        for (Role role : roleList) {
            List<Menu> list = menuService.list(new LambdaQueryWrapper<Menu>().inSql(Menu::getId, "SELECT menu_id FROM role_menu WHERE role_id = " + role.getId()));
            menuSet.addAll(list);
        }

        currentUser.setRoles(roleList.stream().map(Role::getName).distinct().collect(Collectors.joining(",")));

        List<Menu> menuList = new ArrayList<>(menuSet);
        // 排序
        menuList.sort(Comparator.comparing(Menu::getOrderNum));
        // 把菜单转成树结构
        List<MenuVO> menuTree = menuService.buildTreeMenu(menuList);

        outputStream.write(JSON.toJSONString(R.ok("登陆成功").put("data", token).put("currentUser", currentUser).put("menuList", menuTree)).getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
