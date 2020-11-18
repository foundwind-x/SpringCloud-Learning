package com.didispace.config.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author fangzheng
 * @description controller层接口调用时长和入参打印
 */
@Slf4j
@Aspect
@Component
public class RestAspect {

    @Pointcut("execution(public * com.didispace.*.*Controller.*(..))")
    public void restPointCut() {
    }

    @Around("restPointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        long time1=System.currentTimeMillis();
        Object result = point.proceed();
        long time2=System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        //请求的方法名
        String className = signature.getDeclaringTypeName();
        /*String className = point.getTarget().getClass().getName();*/
        String methodName = signature.getName();
        //参数名称
        /*String[] parameterNames = signature.getParameterNames();*/

        StringBuffer sb = new StringBuffer("方法");
        sb.append(className).append(".").append(methodName).append("()");
        sb.append("耗时：").append(time2-time1).append("ms");
        //请求的参数
        Object[] args = point.getArgs();
        try{
            String params = JSONObject.toJSONString(args);
            sb.append("入参：").append(params);
        }catch (Exception e){
            log.error("入参json转化异常");
        }

        log.info(sb.toString());

        return result;
    }
}
