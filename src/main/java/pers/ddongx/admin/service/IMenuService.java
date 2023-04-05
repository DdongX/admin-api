package pers.ddongx.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.ddongx.admin.domain.entity.Menu;
import pers.ddongx.admin.domain.vo.MenuVO;

import java.util.List;

/**
 * description 针对表【Menu】的数据库操作Service
 *
 * @author DdongX
 * @since 2022-12-04 17:10:17
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 把菜单转成树结构
     *
     * @param menuList 菜单列表
     * @return 树结构
     */
    List<MenuVO> buildTreeMenu(List<Menu> menuList);
}
