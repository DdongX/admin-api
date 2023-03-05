package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.Menu;

/**
 * description 针对表【menu(菜单权限表)】的数据库操作Mapper
 * Entity pers.ddongx.admin.domain.entity.Menu
 *
 * @author Ddong
 * @since 2023-02-19 19:12:41
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}