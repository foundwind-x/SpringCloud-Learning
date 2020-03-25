package com.didispace.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName IpConfiguration
 * @Description 获取IP端口号
 * @Author fangzheng
 * @Date 2019/12/25 15:41
 * @Version V1.0
 */
@Component
@Slf4j
public class IpConfiguration implements ApplicationListener<WebServerInitializedEvent> {

    private int serverPort;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

    public int getPort(){
        return this.serverPort;
    }

    public String getHost(){
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.info("获取ip失败", e);
        }

        return ip;
    }

    public String getHostPort(){

        return getHost()+":"+this.serverPort;
    }

}
