package com.example.mqttdemo.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * MQTT连接配置
 *
 * @description:
 * @author: Okentao
 * @create: 2021-02-14 14:17
 */
@Configuration
@IntegrationComponentScan
public class MqttConfig {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    /**
     * 创建连接
     */
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions connect = new MqttConnectOptions();
        connect.setUserName(username);
        connect.setPassword(password.toCharArray());
        connect.setServerURIs(new String[]{hostUrl});
        // 心跳
        connect.setKeepAliveInterval(2);
        // ClearSession的设置为0，QoS为1或2 可以让设备接收离线消息
        connect.setCleanSession(false);
        return connect;
    }

    /**
     * mqtt客户端工厂
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }
}
