//package com.example.service_hi.RabbitMq.rabbitmqs.service.impl.compoent;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitmqMapper {
////    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * 使用 convertAndSend 方法时的结果：输出时没有顺序，不需要等待，直接运行
//     * 使用 convertSendAndReceive 方法时的结果：按照一定的顺序，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
//     */
//
//    public void sendWork() {
//        // 向work模式里面发送消息
//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.convertAndSend("queue_work", "测试work模型: " + i);
//        }
//    }
//
//    public void sendPublish() {
//        for (int i = 0; i < 5; i++) {
//            // rabbitTemplate.convertSendAndReceive("exchange_fanout", "", "测试发布订阅模型：" + i);
//            rabbitTemplate.convertAndSend("exchange_fanout", "", "测试发布订阅模型：" + i);
//        }
//    }
//}
