package com.example.service.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @HystrixCommand(fallbackMethod = "defaultUser")
    public String getUser(String userName) throws Exception {
        if ("S".equals(userName)){
            return  "okkk";
        }else{
            throw  new Exception();
            //throw  new HystrixBadRequestException("HystrixBadRequestException");
        }
    }

    /**
     * getUser 出错时调用该方法，预返回友好提示
     * @param userName
     * @param throwable 错误信息获取 可以不用
     * @return
     */
    public String defaultUser(String userName,Throwable throwable){
        log.info(throwable.getMessage());
        return "1221323--->"+userName ;
    }

}
