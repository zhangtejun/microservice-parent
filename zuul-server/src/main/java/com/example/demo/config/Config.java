package com.example.demo.config;

import com.example.demo.filter.FirstPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public FirstPreFilter firstPreFilter(){
        return new FirstPreFilter();
    }
}
