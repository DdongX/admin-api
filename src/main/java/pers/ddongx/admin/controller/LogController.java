package pers.ddongx.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.ddongx.admin.domain.entity.LogPO;
import pers.ddongx.admin.service.ILogService;

/**
 * 操作日志 前端控制器
 *
 * @author DdongX
 * @since 2023-04-16
 */
@Controller
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final ILogService ILogService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<LogPO>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<LogPO> aPage = ILogService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }


    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        ILogService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

}
