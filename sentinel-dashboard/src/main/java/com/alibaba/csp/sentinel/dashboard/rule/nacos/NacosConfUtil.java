package com.alibaba.csp.sentinel.dashboard.rule.nacos;

/**
 * @ClassName ConfigUtil
 * @Description TODO
 * @Author fangzheng
 * @Date 2020/3/26 20:53
 * @Version V1.0
 */
public class NacosConfUtil {
    /**
     * Nacos group id
     */
    public static final String GROUP_ID = "SENTINEL_GROUP";
    /**
     * 限流规则后缀
     */
    public static final String FLOW_DATA_ID_POSTFIX = "-flow-rules";
    /**
     * 降级规则后缀
     */
    public static final String DEGRADE_DATA_ID_POSTFIX = "-degrade-rules";
    /**
     * 系统规则后缀
     */
    public static final String SYSTEM_DATA_ID_POSTFIX = "-system-rules";
    /**
     * 热点参数限流规则后缀
     */
    public static final String PARAM_FLOW_DATA_ID_POSTFIX = "-param-flow-rules";
    /**
     * 授权规则后缀
     */
    public static final String AUTHORITY_DATA_ID_POSTFIX = "-authority-rules";
}
