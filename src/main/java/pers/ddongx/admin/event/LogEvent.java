package pers.ddongx.admin.event;

import org.springframework.context.ApplicationEvent;
import pers.ddongx.admin.domain.entity.LogPO;

/**
 * Description 操作日志事件
 * <a href="https://juejin.cn/search?query=%E5%BC%82%E6%AD%A5%E8%AE%B0%E5%BD%95%E6%93%8D%E4%BD%9C%E6%97%A5%E5%BF%97&type=0">参考文章</a>
 *
 * @author DdongX
 * @since 2019-04-28 11:34
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(LogPO po) {
        super(po);
    }
}