package pers.ddongx.admin.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.stereotype.Component;

/**
 * 自定义主键生成策略
 *
 * @author DdongX
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Long nextId(Object entity) {
        //返回生成的id值即可.
        return YitIdHelper.nextId();
    }
}