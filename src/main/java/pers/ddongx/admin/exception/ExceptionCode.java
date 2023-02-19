package pers.ddongx.admin.exception;

/**
 * @author DdongX
 * @since 2022/11/16
 */
public interface ExceptionCode {
    /**
     * 获取异常code
     *
     * @return code
     */
    String getCode();

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    String getMessage();
}
