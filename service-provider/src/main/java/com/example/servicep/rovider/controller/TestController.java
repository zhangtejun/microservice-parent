package com.example.servicep.rovider.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@Slf4j
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient client;
;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
        return "Hello World";
    }
}
