package com.example.mqttdemo.service.impl;

import com.example.mqttdemo.service.MqttReceiveService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @description: 消息处理
 * @author: Okentao
 * @create: 2021-02-15 13:26
 */
@Log4j2
@Service
public class MqttReceiveServiceImpl implements MqttReceiveService {

    @Override
    public boolean MqttMessageHandler(String topic, String message) {
        log.info("接受来自" + topic + "的消息->" + message);
        return true;
    }
}
