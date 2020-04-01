package com.fz.cloud.sleuth.api;

import com.fz.cloud.sleuth.api.constant.ApiPathConstant;
import com.fz.cloud.sleuth.api.constant.FeignClientConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fangzheng
 */
@FeignClient(value = FeignClientConstant.FEIGN_CLIENT_SLEUTH_CART,path = FeignClientConstant.FEIGN_CLIENT_PATH_SLEUTH_CART)
public interface CartService {

    /**
     * 加入购物车
     * 只所以不把"ApiPathConstant.PATH_CART"放在类上的RequestMapping注释中是因为与对应实现类CartController中RequestMapping重复的问题
     * @param cartId 购物车id
     * @return
     */
    @GetMapping(ApiPathConstant.PATH_CART+ApiPathConstant.ADD_CART)
    public String addToCart(@PathVariable("cartId") String cartId);
}
