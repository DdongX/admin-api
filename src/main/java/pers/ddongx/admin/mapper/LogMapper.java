package pers.ddongx.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.ddongx.admin.domain.entity.LogPO;

/**
 * 操作日志 Mapper 接口
 *
 * @author DdongX
 * @since 2023-04-16
 */
@Mapper
public interface LogMapper extends BaseMapper<LogPO> {

}
