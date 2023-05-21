package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.ddongx.admin.domain.BaseEntity;
import pers.ddongx.admin.handler.AesEncryptHandler;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author DdongX
 * @since 2023-05-01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("s_user")
public class UserPO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 工号
     */
    private String code;

    /**
     * 密码
     */
    @TableField(typeHandler = AesEncryptHandler.class)
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    @TableField(typeHandler = AesEncryptHandler.class)
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态
     */
    private Integer status;
}
