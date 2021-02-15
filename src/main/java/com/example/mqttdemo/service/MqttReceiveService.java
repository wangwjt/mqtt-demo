package com.example.mqttdemo.service;

/**
 * @description:
 * @author: Okentao
 * @create: 2021-02-15 13:26
 */
public interface MqttReceiveService {

    /**
     * 消息处理
     *
     * @param topic   主题
     * @param message 消息
     */
    boolean MqttMessageHandler(String topic, String message);
}
