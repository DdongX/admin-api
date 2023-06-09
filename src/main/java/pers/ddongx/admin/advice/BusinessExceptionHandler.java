package pers.ddongx.admin.advice;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Throwables;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.ddongx.admin.domain.Result;
import pers.ddongx.admin.exception.BaseException;
import pers.ddongx.admin.exception.BusinessException;
import pers.ddongx.admin.util.LogUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @author DdongX
 * @since 2022/11/16
 */
@RestControllerAdvice
public class BusinessExceptionHandler implements Ordered {

    @Resource
    private MessageSourceHandler messageSourceHandler;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result onException(final Exception exception, final HttpServletRequest request) {
        Result result = null;
        if (exception instanceof BusinessException) {
            final BaseException baseException = (BaseException) exception;
            final List<Throwable> causalChain = Throwables.getCausalChain(exception);
            if (CollUtil.isEmpty(causalChain)) {
                result = Result.fail(request, baseException.getCode(), handleExceptionMessage(baseException.getMessage()));
            } else {
                for (final Throwable throwable : causalChain) {
                    if (throwable instanceof BusinessException) {
                        final BusinessException businessException = (BusinessException) throwable;
                        result = Result.fail(request, businessException.getCode(), handleExceptionMessage(businessException.getMessage()));
                    }
                }
            }
        } else if (exception instanceof MethodArgumentNotValidException) {
            final MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            result = Result.fail(request, "501", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        } else if (exception instanceof IllegalArgumentException) {
            final IllegalArgumentException e = (IllegalArgumentException) exception;
            result = Result.fail(request, "502", Objects.requireNonNull(e.getMessage()));
        } else if (exception instanceof NotLoginException) {
            final NotLoginException e = (NotLoginException) exception;
            result = Result.fail(request, "401", Objects.requireNonNull(e.getMessage()));
        } else if (exception instanceof NotRoleException) {
            final NotRoleException e = (NotRoleException) exception;
            result = Result.fail(request, "402", Objects.requireNonNull(e.getMessage()));
        } else if (exception instanceof NotPermissionException) {
            final NotPermissionException e = (NotPermissionException) exception;
            result = Result.fail(request, "403", Objects.requireNonNull(e.getMessage()));
        } else if (exception instanceof SaTokenException) {
            final SaTokenException e = (SaTokenException) exception;
            result = Result.fail(request, "500", Objects.requireNonNull(e.getMessage()));
        } else if (exception instanceof IllegalStateException) {
            final IllegalStateException e = (IllegalStateException) exception;
            result = Result.fail(request, "503", Objects.requireNonNull(e.getMessage()));
        }
        // 如果result为Null则代表未有命中的异常，则抛出未知异常
        if (result == null) {
            final String trace = Throwables.getStackTraceAsString(exception);
            final String exceptionMessage = "服务异常，请联系管理员！";
            LogUtil.error("exception:" + trace);
            result = Result.fail(request, "500", exceptionMessage);
        }
        return result;
    }

    private String handleExceptionMessage(String msg) {
        final String msgKey = msg;
        try {
            msg = messageSourceHandler.getMessage(msgKey);
        } catch (final Exception ignored) {
        }
        return msg;
    }

    /**
     * 保证在全局异常处理器之后返回
     *
     * @return 1001
     */
    @Override
    public int getOrder() {
        return 1001;
    }

}

