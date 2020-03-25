package com.fz.springcloud.gateway.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName ResponseData
 * @Description 定义Response vo
 * @Author fangzheng
 * @Date 2019/11/14 10:15
 * @Version V1.0
 */
@Data
public class ResponseMessage {

    private Integer status;
    private String error;
    private String message;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd mm:ss")
    private Date timestamp;
}
