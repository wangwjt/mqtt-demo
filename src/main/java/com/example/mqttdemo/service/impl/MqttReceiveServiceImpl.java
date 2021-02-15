package com.example.mqttdemo.service.impl;

import com.example.mqttdemo.service.MqttReceiveService;
import org.springframework.stereotype.Service;

/**
 * @description: 消息处理
 * @author: Okentao
 * @create: 2021-02-15 13:26
 */
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {
    @Override
    public void MqttMessageHandler(String topic, String message) {
        System.out.println("接受来自" + topic + "的->" + message);
    }
}
