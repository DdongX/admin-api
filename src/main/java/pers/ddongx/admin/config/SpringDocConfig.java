package pers.ddongx.admin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * SpringDoc配置类
 *
 * @author Ddong
 * @since 2023-4-16
 */
@Configuration
@AutoConfigureBefore(SpringDocConfiguration.class)
public class SpringDocConfig {

    private static final String TOKEN_HEADER = "token";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(TOKEN_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .scheme("basic")
                                        .name(TOKEN_HEADER)
                                        .in(SecurityScheme.In.HEADER)
                                        .description("请求头")
                        ))
                .info(
                        new Info()
                                .title("文档标题")
                                .description("文档描述")
                                .contact(new Contact().name("DDONGX").email("2510589885@qq.com").url("https://gitee.com/DdongX/admin-api"))
                                .version("0.1")
                )
                // 引入外部的文档，我这里引得是 springdoc 官方文档地址，你可以不配此项
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc官方文档")
                        .url("https://springdoc.org/")
                );
    }

    @Bean
    public GroupedOpenApi sysApi() {
        return GroupedOpenApi.builder()
                .group("系统接口")
                .packagesToScan("pers.ddongx.admin.controller")
                // 添加自定义配置，这里添加了一个用户认证的 header，否则 knife4j 里会没有 header
                .addOperationCustomizer((operation, handlerMethod) -> operation.security(
                        Collections.singletonList(new SecurityRequirement().addList(TOKEN_HEADER)))
                )
                .build();
    }
}