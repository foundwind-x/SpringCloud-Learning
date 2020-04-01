package com.fz.cloud.sleuth.order.controller;

import com.fz.cloud.sleuth.api.OrderService;
import com.fz.cloud.sleuth.api.constant.ApiPathConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description order controller
 * @Author fangzheng
 * @Date 2020/3/31 11:08
 * @Version V1.0
 */
@Slf4j
@RequestMapping(ApiPathConstant.PATH_ORDER)
@RestController
public class OrderController implements OrderService {

    @GetMapping(value = ApiPathConstant.CREATE_ORDER)
    public String creatOrder() {
        log.info("create order");
        return "order created";
    }
}
