package pers.ddongx.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.Role;
import pers.ddongx.admin.mapper.RoleMapper;
import pers.ddongx.admin.service.IRoleService;

/**
 * description 针对表【Role】的数据库操作Service实现
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements IRoleService {
}