package pers.ddongx.admin;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.ddongx.admin.util.LogUtil;

/**
 * @author DdongX
 * @since 2022/12/3
 */
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AdminApplication.class);
            app.run(args);
            // 创建 IdGeneratorOptions 对象，可在构造函数中输入 WorkerId：
            IdGeneratorOptions options = new IdGeneratorOptions();
            // options.WorkerIdBitLength = 10; // 默认值6，限定 WorkerId 最大值为2^6-1，即默认最多支持64个节点。
            // options.SeqBitLength = 6; // 默认值6，限制每毫秒生成的ID个数。若生成速度超过5万个/秒，建议加大 SeqBitLength 到 10。
            // options.BaseTime = Your_Base_Time; // 如果要兼容老系统的雪花算法，此处应设置为老系统的BaseTime。
            // ...... 其它参数参考 IdGeneratorOptions 定义。
            // 保存参数（务必调用，否则参数设置不生效）：
            YitIdHelper.setIdGenerator(options);
            // 以上过程只需全局一次，且应在生成ID之前完成。

            LogUtil.info("AdminApplication App Start !!!!!!");
        } catch (Exception e) {
            LogUtil.error("AdminApplication App Start Failed !!!!!!!!", e);
            throw e;
        }
    }

}
