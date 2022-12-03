package com.example.eureka_client_config_service.Test.service.serveceImpl;

import com.example.eureka_client_config_service.Test.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String hiService(String name) {
        /**
         * 调用其他服务
         */
        return restTemplate.getForObject("http://EUREKA-CLIENT-1/hi?name=" + name, String.class);
    }

    /**
     * ribbon断路器实现的服务熔断
     * 并指定熔断方法
     * @param name
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "ribbonError")
    public String hystrixService(String name) {
        /**
         * 调用其他服务
         */
        return restTemplate.getForObject("http://EUREKA-CLIENT-1/hi?name=" + name, String.class);
    }

    /**
     * ribbon熔断服务熔断时的处理方法
     * @param name
     * @return
     */
    public String ribbonError(String name) {
        return "hi," + name + ",sorry,error!Hystrix~该服务已熔断！";
    }
}
