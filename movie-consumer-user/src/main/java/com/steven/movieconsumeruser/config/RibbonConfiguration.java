package com.steven.movieconsumeruser.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置类
 *
 * @author Steven
 * @date 2018/9/26 026 14:36
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        // 负载规则为随机
        return new RandomRule();
    }
}
