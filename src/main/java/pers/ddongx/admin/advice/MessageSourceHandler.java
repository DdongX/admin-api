package pers.ddongx.admin.advice;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author DdongX
 * @since 2022/11/16
 */
@Component
public class MessageSourceHandler {
    private final MessageSource messageSource;

    public MessageSourceHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String msgKey) {
        return this.messageSource.getMessage(msgKey, (Object[]) null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String msgKey, Object... args) {
        return this.messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());
    }
}