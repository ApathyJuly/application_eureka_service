//package com.example.service_hi.RabbitMq.rabbitmqs.controller;
//
//import com.example.service_hi.RabbitMq.rabbitmqs.service.RabbitmqService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping(value = "/rabbitmqController")
//public class RabbitmqController {
//    @Autowired
//    private RabbitmqService rabbitmqService;
//
//    @GetMapping("/sendWork")
//    @ResponseBody
//    public String sendWork() {
//        System.out.println("sendwork");
//        rabbitmqService.sendWork();
//        return "sendwork发送成功...";
//    }
//
//    @RequestMapping("/sendPublish")
//    @ResponseBody
//    public String sendPublish() {
//        rabbitmqService.sendPublish();
//        return "sendPublish发送成功...";
//    }
//}
