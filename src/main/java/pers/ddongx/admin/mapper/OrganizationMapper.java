package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.OrganizationPO;

/**
 * 组织表Mapper 接口
 *
 * @author DdongX
 * @since 2023-05-01
 */
@Mapper
public interface OrganizationMapper extends BaseMapper<OrganizationPO> {

}
