package pers.ddongx.admin.exception;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author DdongX
 * @since 2022/11/16
 */
public class BusinessException extends BaseException {

    private static final long serialVersionUID = 8413350118692519907L;

    public BusinessException(String message) {
        super("500", message);
    }

    public BusinessException(String message, Object... arg) {
        super("500", MessageFormatter.arrayFormat(message, arg).getMessage());
    }
}
