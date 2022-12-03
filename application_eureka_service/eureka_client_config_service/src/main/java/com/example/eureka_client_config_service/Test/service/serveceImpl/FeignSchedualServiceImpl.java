package com.example.eureka_client_config_service.Test.service.serveceImpl;

import com.example.eureka_client_config_service.Test.service.FeignSchedualService;
import org.springframework.stereotype.Component;

@Component
public class FeignSchedualServiceImpl implements FeignSchedualService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name + "feign所调用的服务不存在！";
    }
}
