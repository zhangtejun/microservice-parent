package com.example.service.invoker.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AviodScan
public class ProviderConfiguration {
   /* @Bean
    public IRule ribbonRule()
    {
        return new RandomRule();
    }*/

   @Autowired
   IClientConfig config;
   @Bean
    public IRule ribbonRule(IClientConfig config)
    {
        //return new RandomRule();
        return new RoundRobinRule();
    }
}
