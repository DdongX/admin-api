package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.ddongx.admin.domain.BaseEntity;

import java.io.Serializable;

/**
 * 菜单权限表
 * TableName menu
 *
 * @author DdongX
 */
@TableName(value = "menu")
@Data
public class Menu extends BaseEntity implements Serializable {

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 父级菜单id
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 顺序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 菜单类型（M目录 C菜单）
     */
    @TableField(value = "type")
    private String type;

    /**
     * 权限表示
     */
    @TableField(value = "perms")
    private String perms;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}