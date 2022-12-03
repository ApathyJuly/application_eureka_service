package com.example.eureka_client_config_service;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication//程序启动
@EnableEurekaClient//注册服务到注册中心
@EnableDiscoveryClient//负载均衡
@EnableHystrixDashboard//仪表盘租注解，开启hystrixDashboard，相当于一个监视器
@EnableCircuitBreaker//使用监视器，springcloud2.0以上@EnableHystrix更改为 @EnableCircuitBreaker
@EnableFeignClients//feign注解提供熔断等功能
public class EurekaClientConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConfigServiceApplication.class, args);
    }

    @Bean//bean交给容器，并在service中使用
    @LoadBalanced
    /**在获取RestTemplate的方法上加了@LoadBalanced ,实现默认轮询式负载均衡。**/
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /**
     * 配置仪表盘的servlet，
     * 版本如果是2.0则需要添加 ServletRegistrationBean 因为springboot的默认路径不是 "/hystrix.stream"，
     * 只要在自己的项目里配置上下面的servlet就可以了
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
