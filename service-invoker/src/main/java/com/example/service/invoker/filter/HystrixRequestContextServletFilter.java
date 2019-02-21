package com.example.service.invoker.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 2 种注册方式
 * 1. @WebFilter 注解 启动类增加 @ServletComponentScan
 * 2. 通过@Configuration注册
 */
@Slf4j
@WebFilter(filterName = "hystrixRequestContextServletFilter",urlPatterns = "/*",asyncSupported = true)
public class HystrixRequestContextServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            log.info("@@@@@@@@@->HystrixRequestContextServletFilter");
            filterChain.doFilter(request, response);
        } finally {
            context.shutdown();
        }

    }
}
