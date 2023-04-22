package pers.ddongx.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * Description: 操作类型，用于日志注解
 *
 * @author Ddong
 * @since 2023/4/16 18:41
 */
@Getter
public enum OperationType {
    OTHER(1, "其他"),
    INSERT(2, "新增"),
    UPDATE(3, "修改"),
    DELETE(4, "删除");

    @EnumValue
    private final int code;
    private final String description;

    OperationType(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
