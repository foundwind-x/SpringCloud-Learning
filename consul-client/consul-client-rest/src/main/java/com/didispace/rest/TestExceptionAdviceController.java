package com.didispace.rest;

import com.didispace.api.model.Result;
import com.didispace.api.model.TestInputVO;
import com.didispace.api.model.TestOutputVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestExceptionAdviceController
 * @Description 测试ControllerExceptionAdvice
 * @Author fangzheng
 * @Date 2019/9/1 15:36
 * @Version V1.0
 */
@RestController
public class TestExceptionAdviceController {

    /**
     * 测试Exception Advice
     * @param name
     * @return
     */
    @GetMapping("/advice/test")
    public String testException(@RequestParam(required = true) String name) {
        int i = 1 / 0;
        return "welcome "+name;
    }

    /**
     * 测试Validate Exception
     * @param testInputVO
     * @return
     */
    @PostMapping("/advice/test")
    public String testValidateException(@RequestBody @Validated TestInputVO testInputVO){
        return testInputVO.getName();
    }
}
