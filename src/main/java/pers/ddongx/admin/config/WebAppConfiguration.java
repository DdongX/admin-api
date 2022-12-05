package pers.ddongx.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: Web项目配置类
 *
 * @author DdongX
 * @since 2022/12/4 23:08
 */
@Configuration
public class WebAppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS").maxAge(3600);
    }
}
