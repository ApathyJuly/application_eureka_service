package com.example.service_hi.springcloudconfig.rabbitmq;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @desccription 自定义RabbitMQ的启动配置类，可以通过配置变量来控制启用、禁用
 * @author apathy
 * @date 2022/10/12
 */
@Configuration
@ConditionalOnProperty("spring.rabbitmq.enable")
public class MyRabbitAutoConfiguration extends RabbitAutoConfiguration {
}