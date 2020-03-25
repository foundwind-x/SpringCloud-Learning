package com.didispace.config.configuration;

import com.didispace.config.filter.SignatureFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration  暂时不用
public class FilterConfigBean {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SignatureFilter());
        registration.addUrlPatterns("/api/publish/*","/api/cbs/*");
        registration.setName("bufferedServletFilter");
        registration.setOrder(1);

        return registration;
    }
}
