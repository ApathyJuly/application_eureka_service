package com.example.eureka_client_config_service.Test.service;

import com.example.eureka_client_config_service.Test.service.serveceImpl.FeignSchedualServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 定义一个feign接口，通过@ FeignClient（"服务名"），来指定调用哪个服务。比如在代码中调用了eureka-client-1服务的/hi接口。
 * 服务不存在或调用不到时执行实现方法。
 */
@FeignClient(value = "eureka-client-1", fallback = FeignSchedualServiceImpl.class)
public interface FeignSchedualService {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(String name);
}
