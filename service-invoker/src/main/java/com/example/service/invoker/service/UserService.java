package com.example.service.invoker.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @HystrixCommand(fallbackMethod = "defaultUser")
    public String getUser(String userName) throws Exception {
        if ("S".equals(userName)){
            return  "okkk";
        }else{
            throw  new Exception();
        }
    }

    /**
     * getUser 出错时调用该方法，预返回友好提示
     * @param userName
     * @return
     */
    public String defaultUser(String userName){
        return "1221323--->"+userName;
    }

}
