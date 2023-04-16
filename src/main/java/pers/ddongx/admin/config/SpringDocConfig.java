package pers.ddongx.admin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
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
                                        // 这里配置 bearer 后，你的请求里会自动在 token 前加上 Bearer
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ).addParameters(TOKEN_HEADER,
                                new Parameter()
                                        .in("header")
                                        .schema(new StringSchema())
                                        .name(TOKEN_HEADER)
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

    /**
     * GroupedOpenApi 是对接口文档分组，类似于 swagger 的 Docket
     *
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                // 组名
                .group("认证接口")
                // 扫描的路径，支持通配符
                .pathsToMatch("/login")
                // 扫描的包
                .packagesToScan("com.demo.controller.auth")
                .build();
    }

    @Bean
    public GroupedOpenApi sysApi() {
        return GroupedOpenApi.builder()
                .group("系统接口")
                .pathsToMatch("/adminapi/**")
                // 添加自定义配置，这里添加了一个用户认证的 header，否则 knife4j 里会没有 header
                .addOperationCustomizer((operation, handlerMethod) -> operation.security(
                        Collections.singletonList(new SecurityRequirement().addList(TOKEN_HEADER)))
                )
                .build();
    }
}