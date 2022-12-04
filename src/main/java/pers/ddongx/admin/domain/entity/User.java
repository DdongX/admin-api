package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.ddongx.admin.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * TableName user
 *
 * @author DdongX
 * @since 2022/12/4
 */
@TableName(value = "user")
@Data
public class User extends BaseEntity implements Serializable {

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 最后登陆时间
     */
    @TableField(value = "gmt_login")
    private Date gmtLogin;

    /**
     * 状态：0-启用 1-停用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}