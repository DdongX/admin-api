package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.ddongx.admin.domain.BaseEntity;
import pers.ddongx.admin.handler.AesEncryptHandler;

import java.io.Serializable;

/**
 * 组织表
 *
 * @author DdongX
 * @since 2023-05-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("s_organization")
public class OrganizationPO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型（节点、单位、部门）
     */
    private Integer type;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 社会统一信用代码
     */
    private String creditCode;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 负责人联系方式
     */
    @TableField(typeHandler = AesEncryptHandler.class)
    private String leaderContact;

    /**
     * 单位所在地
     */
    private String address;

    /**
     * 管辖单位ID
     */
    private Long superiorId;

    /**
     * 父级组织ID
     */
    private Long parentId;

    /**
     * 子节点数量
     */
    private Integer childNum;

}
