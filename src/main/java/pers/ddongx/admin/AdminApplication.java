package pers.ddongx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.ddongx.admin.util.LogUtil;

/**
 * 启动类
 *
 * @author Ddong
 */
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AdminApplication.class);
            app.run(args);
            LogUtil.info("项目启动成功ヾ(✿ﾟ▽ﾟ)ノ");
        } catch (Exception e) {
            LogUtil.error("项目启动失败(；′⌒`)", e);
            throw e;
        }
    }

}
