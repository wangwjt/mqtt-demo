package com.example.mqttdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * MQTT 生产者配置
 *
 * @description: 发送消息
 * @author: Okentao
 * @create: 2021-02-14 13:54
 */
@Configuration
@IntegrationComponentScan
public class MqttSenderConfig {

    @Autowired
    MqttConfig mqttConfig;

    @Value("${spring.mqtt.product.id}")
    private String productId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(productId, mqttConfig.mqttClientFactory());
        // 设置成true，发送消息时将不会阻塞
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}