package com.example.mqttdemo.controller;

import com.example.mqttdemo.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MqttService mqttService;

    /**
     * 推送消息
     */
    @GetMapping("/send")
    public String sendMqtt(@RequestParam("data") String data) {
        mqttService.sendMessage(data, "test/testTopic");
        System.out.println("向test/testTopic发送 " + data);
        return "OK";
    }
}