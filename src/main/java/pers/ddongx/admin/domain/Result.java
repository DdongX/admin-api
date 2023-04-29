package pers.ddongx.admin.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一返回体
 *
 * @author DdongX
 * @since 2022/11/16
 */
@Data
@Schema(name = "Result", description = "统一返回体")
public class Result {
    @Schema(name = "code", description = "状态值")
    private String code;
    @Schema(name = "data", description = "数据主体")
    private Object data;
    @Schema(name = "message", description = "提示语")
    private String message;
    @Schema(name = "timestamp", description = "时间戳")
    private long timestamp;
    @Schema(name = "uri", description = "请求地址")
    private String uri;

    public static Result success(HttpRequest request, Object data) {
        Result result = new Result();
        result.setCode("200");
        result.setData(data);
        result.setTimestamp(System.currentTimeMillis());
        result.setUri(request.getURI().getPath());
        result.setMessage("操作成功");
        return result;
    }

    public static Result fail(HttpServletRequest request, String code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setTimestamp(System.currentTimeMillis());
        result.setMessage(message);
        result.setUri(request.getRequestURI());
        return result;
    }
}
