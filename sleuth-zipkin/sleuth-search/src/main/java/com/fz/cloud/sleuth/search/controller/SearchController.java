package com.fz.cloud.sleuth.search.controller;

import com.fz.cloud.sleuth.api.CartService;
import com.fz.cloud.sleuth.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SearchController
 * @Description search controller
 * @Author fangzheng
 * @Date 2020/3/31 11:58
 * @Version V1.0
 */
@RestController
public class SearchController {

    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;

    @GetMapping("/search")
    public String search(@RequestParam(name = "cartId") String cartId){
        String order_response = orderService.creatOrder();
        String cart_response = cartService.addToCart(cartId);

        return "cart:" + cart_response + ",order:" + order_response;
    }
}
