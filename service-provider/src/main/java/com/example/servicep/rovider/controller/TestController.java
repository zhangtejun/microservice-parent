package com.example.servicep.rovider.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "hello2", method = RequestMethod.GET)
    public String index2() {
        System.err.println("provider  hello2 testing");
        return "Hello World";
    }
    @GetMapping(value = "testRibbon")
    public String testRibbon(HttpServletRequest httpServletRequest) {
        return "-----------> From Port: "+httpServletRequest.getServerPort();
    }
    @RequestMapping("/testHystrixCache/{string}")
    public String testHystrixCache(@PathVariable("string") String string) {
        return "-----------> testHystrixCache ok "+ string ;
    }
}
