package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SystemRuleNacosProvider
 * @Description 从Nacos获取系统规则
 * @Author fangzheng
 * @Date 2020/3/26 20:33
 * @Version V1.0
 */
@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider implements DynamicRuleProvider<List<SystemRuleEntity>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<String, List<SystemRuleEntity>> converter;

    @Override
    public List<SystemRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfUtil.SYSTEM_DATA_ID_POSTFIX, NacosConfUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
