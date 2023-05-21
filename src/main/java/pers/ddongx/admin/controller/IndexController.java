package pers.ddongx.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ddongx.admin.annotation.Log;

/**
 * 测试
 *
 * @author DdongX
 */
@Tag(name = "IndexController", description = "测试Controller")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Operation(summary = "测试方法描述")
    @Log("测试")
    @Parameter(name = "id", description = "ID")
//    @SaIgnore
    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) {
//        throw new BusinessException("测试异常");
        return id;
    }
}
