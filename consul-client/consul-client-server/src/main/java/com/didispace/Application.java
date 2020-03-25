package com.didispace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author zhengfang
 *	Application启动类扫描同级包和下级包的所有类,扫描其他包需要加@ComponentScan
 *  ServletComponentScan注解用于自动扫描指定包下（默认是与启动类同包下）的WebFilter/WebServlet/WebListener等特殊类。
 *  SpringBootApplication注解中的中exclude={DataSourceAutoConfiguration.class,RedisAutoConfiguration.class}作用是，排除自动注入数据源以及redis的配置
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = "com.didispace")
//@ComponentScan(basePackages = {"com.didispace"})  //效果同@SpringBootApplication中的scanBasePackages
//@ServletComponentScan
@EnableFeignClients(basePackages = "com.didispace")
//@EnableConfigurationProperties //使用 @ConfigurationProperties 注解的类生效,转化为Bean
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = new SpringApplicationBuilder(Application.class).run(args);
        /*ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);*/
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application consul-client is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + (path==null?"":path) + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + (path==null?"":path) + "/\n\t" +
//                "swagger-ui: \thttp://" + ip + ":" + port + (path==null?"":path) + "/swagger-ui.html\n\t" +
//                "Doc: \t\thttp://" + ip + ":" + port + (path==null?"":path) + "/doc.html\n" +
                "----------------------------------------------------------");
    }

}
