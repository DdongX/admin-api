package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.User;

/**
 * description 针对表【user】的数据库操作Mapper
 * Entity pers.ddongx.admin.domain.entity.User
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




