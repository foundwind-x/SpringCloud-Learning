package com.didispace.config.advice;

import com.didispace.api.model.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ControllerExceptionAdvice
 * @Description Controller异常捕获处理
 * @Author fangzheng
 * @Date 2019/9/1 15:23
 * @Version V1.0
 */
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Boolean> exceptionHandler(Exception e){
        Result<Boolean> result = new Result<>();
        result.error(e.getMessage());

        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<Boolean> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Result<Boolean> result = new Result<>();
        BindingResult bindingResult = e.getBindingResult();
        result.error(bindingResult.getFieldErrors().get(0).getDefaultMessage());

        return result;
    }
}
