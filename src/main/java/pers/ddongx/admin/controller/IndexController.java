package pers.ddongx.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@Tag(name = "IndexController", description = "测试Controller")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Operation(summary = "测试方法描述")
    @Parameter(name = "ID", description = "ID")
    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
//        throw new BusinessException("测试异常");
        return id;
    }
}
