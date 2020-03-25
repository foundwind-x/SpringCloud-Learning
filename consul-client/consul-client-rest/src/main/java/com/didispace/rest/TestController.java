package com.didispace.rest;

import com.didispace.api.constant.ApiPathConstant;
import com.didispace.api.model.Result;
import com.didispace.api.model.TestInputVO;
import com.didispace.api.model.TestOutputVO;
import com.didispace.api.service.TestRpcService;
import com.didispace.common.constant.LogConst;
import com.didispace.common.util.IpConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;

/**
 * 测试Feign
 */
@RestController
public class TestController implements TestRpcService {

    @Autowired
    IpConfiguration ipConfig;

    @GetMapping(value = ApiPathConstant.TEST)
    @Override
    public String testGet(String name) {
        return ipConfig.getHostPort()+"-hello,"+name;
    }

    @PostMapping(value = ApiPathConstant.TEST)
    @Override
    public Result<TestOutputVO> testPost(@RequestBody TestInputVO testInputVO){
        Result<TestOutputVO> result = new Result<>();
        TestOutputVO vo = new TestOutputVO();
        vo.setInfo("hello,"+testInputVO.getName());
        result.ok(vo);
        return result;
    }
}
