package pers.ddongx.admin.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * Description: 基类
 *
 * @author DdongX
 * @since 2023/5/4 20:48
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
    private String creator;

    /**
     * 创建人ID
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改人ID
     */
    private Long modifyId;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 删除时间
     */
    private Date gmtDelete;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean deleted;
}
