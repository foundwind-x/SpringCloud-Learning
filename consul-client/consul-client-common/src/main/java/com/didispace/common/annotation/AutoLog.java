package com.didispace.common.annotation;

import com.didispace.common.constant.LogConst;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 系统日志注解
 * @Author fangzheng
 * @Date 2019/12/10 10:38
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

	/**
	 * 日志内容
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 日志类型
	 */
	int logType() default LogConst.LOG_TYPE_OPERATION;
}
