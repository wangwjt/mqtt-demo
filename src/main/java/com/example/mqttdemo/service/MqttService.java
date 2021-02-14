package com.example.mqttdemo.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 发送message接口
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttService {

    /**
     * 发送消息
     *
     * @param data  消息内容
     * @param topic 主题名
     */
    void sendMessage(String data, @Header(MqttHeaders.TOPIC) String topic);
}