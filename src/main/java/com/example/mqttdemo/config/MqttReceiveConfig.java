package com.example.mqttdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * MQTT消费者配置
 *
 * @description: MQTT消费者
 * @author: Okentao
 * @create: 2021-02-14 13:48
 */
@Configuration
@IntegrationComponentScan
public class MqttReceiveConfig {

    @Autowired
    MqttConfig mqttConfig;

    @Value("${spring.mqtt.consumer.id}")
    private String consumerId;

    @Value("${spring.mqtt.timeout}")
    private int timeout;

    /**
     * 监听的主题
     */
    private final static String[] listenTopic = {"topic_index", "test/testTopic"};

    /**
     * 接收通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * 配置client, 监听topic
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(consumerId, mqttConfig.mqttClientFactory(), listenTopic);
        adapter.setCompletionTimeout(timeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 通过通道获取数据
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                // 消息主题
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                // 消息主体
                String msg = message.getPayload().toString();
                System.out.println("接受来自" + topic + "的->" + msg);
            }
        };
    }
}
