package com.fz.cloud.sleuth.api;

import com.fz.cloud.sleuth.api.constant.ApiPathConstant;
import com.fz.cloud.sleuth.api.constant.FeignClientConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName OrderService
 * @Description order service
 * @Author fangzheng
 * @Date 2020/3/30 23:35
 * @Version V1.0
 */
@FeignClient(value = FeignClientConstant.FEIGN_CLIENT_SLEUTH_ORDER,path = FeignClientConstant.FEIGN_CLIENT_PATH_SLEUTH_ORDER)
public interface OrderService {

    /**
     * 只所以不把"ApiPathConstant.PATH_ORDER"放在类上的RequestMapping注释中是因为与对应实现类OrderController中RequestMapping重复的问题
     * @return
     */
    @GetMapping(value = ApiPathConstant.PATH_ORDER+ApiPathConstant.CREATE_ORDER)
    public String creatOrder();
}
