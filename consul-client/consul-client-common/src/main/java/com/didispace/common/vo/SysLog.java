package com.didispace.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysLog
 * @Description
 * @Author fangzheng
 * @Date 2019/12/10 11:03
 * @Version V1.0
 */
@Data
public class SysLog implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 耗时
     */
    private Long costTime;

    /**
     * IP
     */
    private String ip;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作人用户名称
     */
    private String username;
    /**
     * 操作人用户账户
     */
    private String userid;
    /**
     * 操作详细日志
     */
    private String logContent;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     * 操作类型（）
     */
    private Integer operateType;
}
