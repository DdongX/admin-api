package pers.ddongx.admin.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import pers.ddongx.admin.domain.Result;

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
    public boolean supports(@NonNull MethodParameter methodParameter, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType, @NonNull Class<? extends HttpMessageConverter<?>> aClass, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {

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
