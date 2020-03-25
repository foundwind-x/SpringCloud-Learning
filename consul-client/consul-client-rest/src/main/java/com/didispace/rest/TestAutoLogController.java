package com.didispace.rest;

import com.didispace.api.constant.ApiPathConstant;
import com.didispace.common.annotation.AutoLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestAutoLogController
 * @Description 测试日志切面
 * @Author fangzheng
 * @Date 2019/12/10 17:46
 * @Version V1.0
 */
@RestController
public class TestAutoLogController {

    @GetMapping(value = "log/test")
    @AutoLog
    public String test(String name) {
        return "test AutoLog aspect";
    }
}
