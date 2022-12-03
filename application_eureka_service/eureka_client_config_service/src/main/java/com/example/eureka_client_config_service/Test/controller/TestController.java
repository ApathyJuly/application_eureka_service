package com.example.eureka_client_config_service.Test.controller;

import com.example.eureka_client_config_service.Test.service.FeignSchedualService;
import com.example.eureka_client_config_service.Test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    /**
     * 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
     */
    /**
     * 相同点：
     *
     * @Resource的作用相当于@Autowired，均可标注在字段或属性的setter方法上。
     *
     * 不同点：
     *
     * （1）提供方：@Autowired是由org.springframework.beans.factory.annotation.Autowired提供，换句话说就是由Spring提供；@Resource是由javax.annotation.Resource提供，即J2EE提供，需要JDK1.6及以上。
     *
     * （2）注入方式：@Autowired只按照byType 注入；@Resource默认按byName自动注入，也提供按照byType 注入；
     *
     * （3）属性：@Autowired按类型装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它required属性为false。
     * 如果我们想使用按名称装配，可以结合@Qualifier注解一起使用。@Resource有两个中重要的属性：name和type。name属性指定byName，如果没有指定name属性，当注解标注在字段上，即默认取字段的名称作为bean名称寻找依赖对象，当注解标注在属性的setter方法上，即默认取属性名作为bean名称寻找依赖对象。需要注意的是，@Resource如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时， @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了。
     */
//    @Autowired
    @Autowired(required=false)
//    @Qualifier
    @Resource
    private FeignSchedualService feignSchedualService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String hi(@RequestParam(value = "name", defaultValue = "testhi") String name) {
        return testService.hiService(name);
    }
    /**
     * ribbon断路器调用服务熔断
     * value不能为已经有的bean，如/hystrix等。
     */
    @RequestMapping(value = "/hystrixService")
    @ResponseBody
    public String hystrixError(@RequestParam(value = "name", defaultValue = "hystrix") String name) {
        return testService.hystrixService(name);
    }

    /**
     * feign可以提供负载均衡和服务熔断能力
     * @param name
     * @return
     */
    @RequestMapping(value = "/FeignError")
    @ResponseBody
    public String feignError(@RequestParam(value = "name", defaultValue = "hystrix") String name){

        return feignSchedualService.sayHiFromClientOne(name);
    }

}
