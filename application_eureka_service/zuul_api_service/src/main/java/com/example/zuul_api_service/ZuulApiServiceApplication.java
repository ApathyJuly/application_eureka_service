package com.example.zuul_api_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 路由网关zuul注解，
 * Zuul的主要功能是路由转发和过滤器。
 * 路由功能是微服务的一部分，比如／api/user转发到到user服务，
 * /api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。
 * zuul功能如下：
 * Authentication(身份验证)
 * Insights()
 * Stress Testing(压力测试)
 * Canary Testing(金丝雀测试)
 * Dynamic Routing(动态路由)
 * Service Migration(服务前移)
 * Load Shedding()
 * Security(安全配置)
 * Static Response handling(静态响应处理)
 * Active/Active traffic management(活动)
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApiServiceApplication {
    /**
     * 开启并使用zuul路由网关
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ZuulApiServiceApplication.class, args);
    }

}
