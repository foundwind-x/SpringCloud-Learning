package com.didispace.rest;

import com.didispace.api.constant.ApiPathConstant;
import com.didispace.api.model.Result;
import com.didispace.api.model.TestInputVO;
import com.didispace.api.model.TestOutputVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author fangzheng
 * @description 测试RestAspect应用
 */
@RestController
@RequestMapping("aspect")
public class TestAspectController {

    @GetMapping("get")
    public String testGet(String name,@RequestParam(name="word",required = false) String word) {
        return (StringUtils.isNotBlank(word)?word:"hello")+","+name;
    }

    @PostMapping("post")
    public String testPost(@RequestBody TestInputVO testInputVO,@RequestParam(value = "word",required = false) String word){

        return (StringUtils.isNotBlank(word)?word:"hello")+","+testInputVO.getName();
    }
}
