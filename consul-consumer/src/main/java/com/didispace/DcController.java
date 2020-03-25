package com.didispace;

import com.didispace.api.constant.FeignClientConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 *
 */
@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
/*        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("consul-client");*/
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-client");
        URI uri = serviceInstance.getUri();
        System.out.println("===uri==="+uri);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+ FeignClientConstant.FEIGN_CLIENT_PATH + "/dc";
        System.out.println("===url==="+url);
        return restTemplate.getForObject(url, String.class);
    }

}
