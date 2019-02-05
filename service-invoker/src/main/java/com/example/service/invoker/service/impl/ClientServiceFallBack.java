package com.example.service.invoker.service.impl;

import com.example.service.invoker.service.ClientService;
import org.springframework.stereotype.Component;

@Component
public class ClientServiceFallBack implements ClientService{
    @Override
    public String index() {
        return "index error";
    }

    @Override
    public String index1() {
        return "index1 error";
    }
}
