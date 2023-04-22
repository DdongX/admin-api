package pers.ddongx.admin.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.ddongx.admin.domain.entity.LogPO;
import pers.ddongx.admin.event.LogEvent;
import pers.ddongx.admin.service.ILogService;

/**
 * Description 注解形式的监听 异步监听日志事件
 *
 * @author DdongX
 * @since 2019-04-28 11:34
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogListener {

    private final ILogService logService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        LogPO log = (LogPO) event.getSource();
        // 保存日志
        logService.save(log);
    }
}