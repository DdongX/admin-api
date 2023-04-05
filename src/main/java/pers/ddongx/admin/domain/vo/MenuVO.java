package pers.ddongx.admin.domain.vo;

import lombok.Data;
import pers.ddongx.admin.domain.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表
 * TableName menu
 *
 * @author DdongX
 */
@Data
public class MenuVO extends BaseEntity implements Serializable {

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单类型（M目录 C菜单）
     */
    private String type;

    /**
     * 权限表示
     */
    private String perms;

    /**
     * 备注
     */
    private String remark;

    private List<MenuVO> children = new ArrayList<>();

    private static final long serialVersionUID = 1L;

}