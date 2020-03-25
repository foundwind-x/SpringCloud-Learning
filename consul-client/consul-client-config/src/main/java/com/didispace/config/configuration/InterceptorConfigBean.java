package com.didispace.config.configuration;

import com.didispace.config.interceptor.APIInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration  //暂时不用
@EnableWebMvc
public class InterceptorConfigBean implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(apiInterceptor());
		//配置拦截路径
		registration.addPathPatterns("/api/publish/**");
		//配置不拦截的路径
		//registration.excludePathPatterns("/user/login.html","/logon/**","/hello","/error","/errorPage");
		registration.order(1);
		
	}

	@Bean
	public APIInterceptor apiInterceptor(){
	    return  new APIInterceptor();
    }

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//System.out.println("----access in messageConverter----");
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter =new MappingJackson2HttpMessageConverter();

		ObjectMapper objectMapper =new ObjectMapper();

		/**
		 * 序列换成json时,将所有的long变成string
 		 * 因为js中得数字类型不能包含所有的java long值
		 */
		SimpleModule simpleModule =new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);

		//日期格式转化
		//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
		//objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		jackson2HttpMessageConverter.setObjectMapper(objectMapper);
		converters.add(jackson2HttpMessageConverter);

	}


}
