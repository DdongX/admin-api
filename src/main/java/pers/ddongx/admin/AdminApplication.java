package pers.ddongx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.ddongx.admin.util.LogUtil;

@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication app = new SpringApplication(AdminApplication.class);
            app.run(args);
            LogUtil.info("AdminApplication App Start !!!!!!");
        } catch (Exception e) {
            LogUtil.error("AdminApplication App Start Failed !!!!!!!!", e);
            throw e;
        }
    }

}
