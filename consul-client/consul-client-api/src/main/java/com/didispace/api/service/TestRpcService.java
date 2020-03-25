package com.didispace.api.service;

import com.didispace.api.constant.ApiPathConstant;
import com.didispace.api.constant.FeignClientConstant;
import com.didispace.api.model.Result;
import com.didispace.api.model.TestInputVO;
import com.didispace.api.model.TestOutputVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author fangzheng
 */
@FeignClient(value = FeignClientConstant.FEIGN_CLIENT,path = FeignClientConstant.FEIGN_CLIENT_PATH)
public interface TestRpcService {
    /**
     * for test
     * @param name
     * @return
     */
    @GetMapping(value = ApiPathConstant.TEST)
    String testGet(@RequestParam(name = "name") String name);

    /**
     *
     * @param testInputVO
     * @return
     */
    @PostMapping(value = ApiPathConstant.TEST)
    Result<TestOutputVO> testPost(TestInputVO testInputVO);
}
