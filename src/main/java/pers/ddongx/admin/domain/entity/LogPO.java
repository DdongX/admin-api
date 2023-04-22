package pers.ddongx.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import pers.ddongx.admin.enums.OperationType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author DdongX
 * @since 2023-04-16
 */
@Data
@TableName("s_log")
@Accessors(chain = true)
public class LogPO implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 操作
     */
    private String operation;

    /**
     * 操作人
     */
    private String creator;

    /**
     * 操作人ID
     */
    private Long creatorId;

    /**
     * 操作类型
     */
    private OperationType operationType;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestData;

    /**
     * 返回值
     */
    private String responseData;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 操作耗时
     */
    private Long duration;

    /**
     * 操作时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

}
