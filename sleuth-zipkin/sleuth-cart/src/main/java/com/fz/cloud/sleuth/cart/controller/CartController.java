package com.fz.cloud.sleuth.cart.controller;

import com.fz.cloud.sleuth.api.CartService;
import com.fz.cloud.sleuth.api.constant.ApiPathConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CartController
 * @Description TODO
 * @Author fangzheng
 * @Date 2020/3/31 18:00
 * @Version V1.0
 */
@Slf4j
@RestController
@RequestMapping(ApiPathConstant.PATH_CART)
public class CartController implements CartService {

    @GetMapping(ApiPathConstant.ADD_CART)
    public String addToCart(@PathVariable("cartId")String cartId) {
        log.info("ID:{} add to cart", cartId);
        return "cart added";
    }
}
