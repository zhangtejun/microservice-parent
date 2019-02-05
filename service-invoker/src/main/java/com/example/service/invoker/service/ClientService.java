package com.example.service.invoker.service;

import com.example.service.invoker.service.impl.ClientServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 绑定服务 service-provider
 */
@FeignClient(value = "service-provider",fallback = ClientServiceFallBack.class)
public interface ClientService {

    @GetMapping(value = "hello")
    String index();
    @PostMapping(value = "hello1")
    String index1();
}
