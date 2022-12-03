package pers.ddongx.admin.exception;

/**
 * @author xixd
 * @since 2022/8/12
 */
public enum AdminExceptionCode implements ExceptionCode {
    /**
     * 服务异常
     */
    SERVICE_ERROR("500", "SERVICE_ERROR");

    private final String code;

    private final String message;

    AdminExceptionCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
