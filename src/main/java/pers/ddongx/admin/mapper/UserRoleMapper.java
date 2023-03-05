package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.UserRole;

/**
 * description 针对表【user_role(用户角色关系表)】的数据库操作Mapper
 * Entity pers.ddongx.admin.domain.entity.UserRole
 *
 * @author DdongX
 * @since 2023-02-19 19:12:41
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
