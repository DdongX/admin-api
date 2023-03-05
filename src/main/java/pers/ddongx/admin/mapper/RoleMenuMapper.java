package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.RoleMenu;

/**
 * description 针对表【role_menu(角色菜单关系表)】的数据库操作Mapper
 * Entity pers.ddongx.admin.domain.entity.RoleMenu
 *
 * @author Ddong
 * @since 2023-02-19 19:12:41
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}