package com.example.service.invoker.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class FeignTokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("---------------> start FeignTokenInterceptor ...");
        if (null == getHttpServletRequest()){
            return;
        }
        log.info("---------------> token "+getHttpServletRequest().getHeader("token"));
        // 将获取到的token继续往下传
        requestTemplate.header("token",getHttpServletRequest().getHeader("token"));
    }
    public HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
