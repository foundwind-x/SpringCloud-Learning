package com.didispace.config.aspect;

import com.alibaba.fastjson.JSONObject;
import com.didispace.common.annotation.AutoLog;
import com.didispace.common.vo.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName AutoLogAspect
 * @Description 系统日志，切面处理类
 * @Author fangzheng
 * @Date 2019/12/10 10:38
 * @Version V1.0
 */
@Aspect
@Component
@Slf4j
public class AutoLogAspect {

    @Pointcut("@annotation(com.didispace.common.annotation.AutoLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        AutoLog autoLog = method.getAnnotation(AutoLog.class);
        if(autoLog != null){
            //注解上的描述,操作日志内容
            sysLog.setLogContent(autoLog.value());
            sysLog.setLogType(autoLog.logType());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSONObject.toJSONString(args);
            sysLog.setRequestParam(params);
        }catch (Exception e){

        }

        //耗时
        sysLog.setCostTime(time);
        sysLog.setCreateTime(new Date());
        log.info(JSONObject.toJSONString(sysLog));
    }
}
