package pers.ddongx.admin.event;

import org.springframework.context.ApplicationEvent;
import pers.ddongx.admin.domain.entity.LogPO;

/**
 * Description 操作日志事件
 *
 * @author DdongX
 * @since 2019-04-28 11:34
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(LogPO po) {
        super(po);
    }
}