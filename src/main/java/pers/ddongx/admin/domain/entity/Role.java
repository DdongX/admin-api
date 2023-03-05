package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.ddongx.admin.domain.BaseEntity;

import java.io.Serializable;

/**
 * 角色表
 * TableName role
 *
 * @author DdongX
 */
@TableName(value = "role")
@Data
public class Role extends BaseEntity implements Serializable {

    /**
     * 角色名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色权限字符串
     */
    @TableField(value = "code")
    private String code;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}