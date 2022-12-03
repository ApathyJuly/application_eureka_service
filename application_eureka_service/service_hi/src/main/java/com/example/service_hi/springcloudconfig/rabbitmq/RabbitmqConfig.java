//package com.example.service_hi.springcloudconfig.rabbitmq;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//
//@SpringBootConfiguration
//public class RabbitmqConfig {
//    /**
//     * 配置工作模型队列
//     * @return
//     */
//    @Bean
//    public Queue queueWork1() {
//        return new Queue("queue_work");
//    }
//
//    @Bean
//    public Queue queueFanout1() {
//        return new Queue("queue_fanout1");
//    }
//
//    @Bean
//    public Queue queueFanout2() {
//        return new Queue("queue_fanout2");
//    }
//
//    /**
//     * 发布订阅模型
//     * @return
//     */
//    // 准备一个交换机
//    @Bean
//    public FanoutExchange exchangeFanout() {
//        return new FanoutExchange("exchange_fanout");
//    }
//
//    // 将交换机和队列进行绑定
//    @Bean
//    public Binding bindingExchange1() {
//        return BindingBuilder.bind(queueFanout1()).to(exchangeFanout());
//    }
//
//    @Bean
//    public Binding bindingExchange2() {
//        return BindingBuilder.bind(queueFanout2()).to(exchangeFanout());
//    }
//}
