package pers.ddongx.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class Swagger3Configuration {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("宜春学院校友会")
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.ddongx.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    @SuppressWarnings("all")
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "用户权限管理系统",
                "用户权限管理系统接口说明文档",
                "v1.0",
                "DdongX@outlook.com", //开发者团队的邮箱
                "DdongX",
                "",  //许可证
                "" //许可证链接
        );
    }
}