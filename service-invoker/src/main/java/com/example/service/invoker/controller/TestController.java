package com.example.service.invoker.controller;

import com.example.service.invoker.service.ClientService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
@RestController
public class TestController {
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientService clientService;

/*    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public Object index() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
         Map map = new HashMap<>();
         map.put("aa","21321");
         map.put("bb", restTemplate.getForObject("http://service-provider:7000/hello",String.class));
         return map;
    }*/

    @RequestMapping(value = "hello1", method = RequestMethod.GET)
    public Object index() {
        List<String> services = client.getServices();
        services.stream().forEach(System.out::println);
         Map map = new HashMap<>();
         map.put("aa","21321");
         map.put("bb", clientService.index());
         return map;
    }

    public static void main(String[] args) {
        System.out.println(12390-2080-100-7*21-30*36);
    }
}
