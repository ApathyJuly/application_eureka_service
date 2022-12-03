//package com.example.service_hi.RabbitMq.rabbitmqs.service.impl.compoent;
//
//import com.rabbitmq.client.Channel;//注意包路径，使用amqp下的
//import org.springframework.amqp.core.Message;//注意包路径，使用amqp下的
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WorkReceiveListener {
//    /**
//     * work模型
//     * @param msg
//     * @param channel
//     * @param message
//     */
//    @RabbitListener(queues = "queue_work")
//    public void receiveMessage(String msg, Channel channel, Message message) {
//        // 只包含发送的消息
//        System.out.println("one接收到消息：" + msg);
//        // channel 通道信息
//        // message 附加的参数信息
//    }
//
//    @RabbitListener(queues = "queue_work")
//    public void receiveMessageTwo(Object obj, Channel channel, Message message) {
//        // 包含所有的信息
//        System.out.println("two接收到消息：" + obj);
//    }
//}
