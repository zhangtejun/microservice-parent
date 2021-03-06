package com.example.service.invoker.config;
import com.example.service.invoker.command.TestHystrixConcurrencyStrategy;
import com.example.service.invoker.interceptor.CacheInterceptor;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebComponent2Config {
    /*@Bean
    public FilterRegistrationBean someFilterRegistration1() {
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        //registration.setFilter( new HystrixRequestContextServletFilter());
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }*/

    @Autowired
    public CacheInterceptor cacheInterceptor;
    @Autowired
    public TestHystrixConcurrencyStrategy testHystrixConcurrencyStrategy;
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(cacheInterceptor).addPathPatterns("/**");
            }
        };
        return webMvcConfigurer;
    }
    /*@PostConstruct
    public void init() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(testHystrixConcurrencyStrategy);
    }*/
}
