package com.didispace;

import com.didispace.api.constant.ApiPathConstant;
import com.didispace.api.model.Result;
import com.didispace.api.model.TestInputVO;
import com.didispace.api.model.TestOutputVO;
import com.didispace.api.service.TestRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fangzheng
 */
@RestController
public class TestController {
    @Autowired
    TestRpcService testRpcService;

    @GetMapping(value = ApiPathConstant.TEST)
    public String testGet(String name){
        String s = testRpcService.testGet(name);
        return s;
    }

    @PostMapping(value = ApiPathConstant.TEST)
    public Result<TestOutputVO> testPost(@RequestBody TestInputVO testInputVO){
        Result<TestOutputVO> result = testRpcService.testPost(testInputVO);
        return result;
    }
}
