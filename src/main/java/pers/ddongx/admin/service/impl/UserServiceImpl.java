package pers.ddongx.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.Menu;
import pers.ddongx.admin.domain.entity.Role;
import pers.ddongx.admin.domain.entity.User;
import pers.ddongx.admin.mapper.MenuMapper;
import pers.ddongx.admin.mapper.RoleMapper;
import pers.ddongx.admin.mapper.UserMapper;
import pers.ddongx.admin.service.IUserService;
import pers.ddongx.admin.util.LogUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description 针对表【user】的数据库操作Service实现
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements IUserService {

    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    @Override
    public User getByUserName(String userName) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getName, userName));
    }

    @Override
    public String getUserAuthorityInfo(Long userId) {
        StringBuilder sb = new StringBuilder();
        // 根据用户id获取所有角色信息
        List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>().inSql("id", "SELECT role_id FROM user_role WHERE user_id = " + userId));
        String roleCode = roleList.stream().map(l -> "ROLE_" + l.getCode()).collect(Collectors.joining(","));
        sb.append(roleCode);
        if (CollUtil.isNotEmpty(roleList)) {
            String roleIds = roleList.stream().map(Role::getId).map(String::valueOf).collect(Collectors.joining(","));
            List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>().inSql(Menu::getId, "SELECT menu_id FROM role_menu WHERE role_id IN (" + roleIds + ")"));
            String menuPerms = menuList.stream().map(Menu::getPerms).filter(StrUtil::isNotBlank).distinct().collect(Collectors.joining(","));
            sb.append(",").append(menuPerms);
        }
        LogUtil.info(sb.toString());
        // 遍历所有角色，获取所有菜单权限，去重
        return sb.toString();
    }
}