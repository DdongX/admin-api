package pers.ddongx.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.OrganizationPO;
import pers.ddongx.admin.mapper.OrganizationMapper;
import pers.ddongx.admin.service.IOrganizationService;

/**
 * 组织服务实现类
 *
 * @author DdongX
 * @since 2023-05-01
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, OrganizationPO> implements IOrganizationService {

}
