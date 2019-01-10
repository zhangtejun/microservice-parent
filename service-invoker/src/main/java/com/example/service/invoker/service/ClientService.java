package com.example.service.invoker.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 绑定服务 service-provider
 */
@FeignClient(value = "service-provider")
public interface ClientService {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    String index();
}
