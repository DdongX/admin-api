package pers.ddongx.admin.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: 实体类基类
 *
 * @author DdongX
 * @since 2022/12/4 17:07
 */
@Data
public class BaseEntity {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 创建人ID
     */
    @TableField(value = "creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改人
     */
    @TableField(value = "modifier")
    private String modifier;

    /**
     * 修改人ID
     */
    @TableField(value = "modify_id")
    private Long modifyId;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;
}
