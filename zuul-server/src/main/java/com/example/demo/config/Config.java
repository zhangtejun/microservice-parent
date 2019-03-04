package com.example.demo.config;

import com.example.demo.filter.FirstPreFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class Config {
    @Bean
    public FirstPreFilter firstPreFilter(){
        return new FirstPreFilter();
    }
}
