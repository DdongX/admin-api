package pers.ddongx.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pers.ddongx.admin.domain.entity.Menu;
import pers.ddongx.admin.domain.vo.MenuVO;
import pers.ddongx.admin.mapper.MenuMapper;
import pers.ddongx.admin.service.IMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * description 针对表【Menu】的数据库操作Service实现
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements IMenuService {
    @Override
    public List<MenuVO> buildTreeMenu(List<Menu> menuList) {
        List<MenuVO> list = new ArrayList<>();
        List<MenuVO> voList = BeanUtil.copyToList(menuList, MenuVO.class);
        for (MenuVO menu : voList) {
            for (MenuVO vo : voList) {
                if (vo.getParentId().equals(menu.getId())) {
                    menu.getChildren().add(vo);
                }
            }
            if (menu.getParentId() == 0) {
                list.add(menu);
            }
        }
        return list;
    }
}