package com.example.service.invoker.controller;

import com.example.service.invoker.config.HystrixThreadLocal;
import com.example.service.invoker.service.ThreadContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@Slf4j
public class ThreadContextController {
    @Autowired
    private ThreadContextService threadContextService;
    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable("id") Integer id){
        // 第一个种情况，放入上下文
        HystrixThreadLocal.threadLocal.set("userId : "+ id);
        // 第二种情况 用RequestContextHolder 存放
        RequestContextHolder.currentRequestAttributes().setAttribute("userId","userId : "+id, RequestAttributes.SCOPE_REQUEST);
        log.info("ThreadContextController ,Current thread : " + Thread.currentThread().getName() + " : id--->  " + Thread.currentThread().getId());
        log.info("ThreadContextController ,ThreadContext object  : " + HystrixThreadLocal.threadLocal.get());
        log.info("ThreadContextController "+RequestContextHolder.currentRequestAttributes().getAttribute("userId",RequestAttributes.SCOPE_REQUEST));
        return threadContextService.getUser(id);
    }
}
