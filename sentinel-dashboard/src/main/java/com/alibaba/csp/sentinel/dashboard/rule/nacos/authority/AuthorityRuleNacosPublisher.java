package com.alibaba.csp.sentinel.dashboard.rule.nacos.authority;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName AuthorityRuleNacosPublisher
 * @Description 推送授权规则到Nacos
 * @Author fangzheng
 * @Date 2020/3/26 20:18
 * @Version V1.0
 */
@Component("authorityRuleNacosPublisher")
public class AuthorityRuleNacosPublisher implements DynamicRulePublisher<List<AuthorityRuleEntity>> {

    @Autowired
    private ConfigService configService;
    @Autowired
    private Converter<List<AuthorityRuleEntity>, String> converter;

    @Override
    public void publish(String app, List<AuthorityRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfUtil.AUTHORITY_DATA_ID_POSTFIX, NacosConfUtil.GROUP_ID, converter.convert(rules));
    }
}
