package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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
 * @ClassName ParamFlowRuleNacosProvider
 * @Description 从Nacos获取参数限流规则
 * @Author fangzheng
 * @Date 2020/3/26 20:24
 * @Version V1.0
 */
@Component("paramFlowRuleNacosProvider")
public class ParamFlowRuleNacosProvider implements DynamicRuleProvider<List<ParamFlowRuleEntity>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<String, List<ParamFlowRuleEntity>> converter;

    @Override
    public List<ParamFlowRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfUtil.PARAM_FLOW_DATA_ID_POSTFIX, NacosConfUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }
}
