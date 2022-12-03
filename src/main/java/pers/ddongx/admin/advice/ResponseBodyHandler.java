package pers.ddongx.admin.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.ddongx.admin.domain.Result;
import pers.ddongx.admin.util.LogUtil;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author DdongX
 * @since 2022/11/16
 */
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseBodyHandler() {
    }

    @Override
    public boolean supports(@Nonnull MethodParameter methodParameter, @Nonnull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, @Nonnull MethodParameter methodParameter, @Nonnull MediaType mediaType, @Nonnull Class<? extends HttpMessageConverter<?>> aClass, @Nonnull ServerHttpRequest request, @Nonnull ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理   否则将出现类型转换异常
        if (o instanceof String) {
            try {
                return this.objectMapper.writeValueAsString(Result.success(request, o));
            } catch (Exception e) {
                LogUtil.error(e.getMessage(), e);
            }
        }

        if (o instanceof Result) {
            return o;
        } else {
            // 取请求头referer头部
            List<String> referer = request.getHeaders().get("referer");
            if (referer != null) {

                for (String ref : referer) {
                    if (ref.endsWith("swagger-ui") || ref.endsWith("swagger-ui/index.html") || ref.endsWith("doc.html")) {
                        return o;
                    }
                }
            }

            return Result.success(request, o);
        }
    }
}
