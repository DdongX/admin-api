package pers.ddongx.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.LogPO;
import pers.ddongx.admin.mapper.LogMapper;
import pers.ddongx.admin.service.ILogService;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author DdongX
 * @since 2023-04-16
 */
@Service
public class ILogServiceImpl extends ServiceImpl<LogMapper, LogPO> implements ILogService {

}
