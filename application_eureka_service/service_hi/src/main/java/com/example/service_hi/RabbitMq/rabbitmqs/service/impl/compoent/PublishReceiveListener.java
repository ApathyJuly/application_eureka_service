//package com.example.service_hi.RabbitMq.rabbitmqs.service.impl.compoent;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PublishReceiveListener {
//
//    @RabbitListener(queues = "queue_fanout1")
//    public void receiveMsgOne(String msg) {
//        System.out.println("队列ONE接收到消息：" + msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout2")
//    public void receiveMsgTwo(String msg) {
//        System.out.println("队列TWO接收到消息：" + msg);
//    }
//}