package com.example.mqttdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mqttdemo.cache.LocalCache;
import com.example.mqttdemo.config.MqttReceiveConfig;
import com.example.mqttdemo.service.MqttService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class TestController {

    @Autowired
    private MqttService mqttService;

    @Autowired
    MqttReceiveConfig mqttReceiveConfig;

    /**
     * 侦听设备的 topic
     */
    @GetMapping("/listen")
    public String sendTaskDistributionMsg(@RequestParam("topic") String topic) {
        // 订阅这个主题
        mqttReceiveConfig.addListTopic(new String[]{topic});
        return topic + "订阅成功";
    }

    /**
     * 推送消息
     */
    @GetMapping("/send")
    public String sendConfigMsg(@RequestParam("topic") String topic, @RequestParam("data") String data) {
        Map msg = new HashMap();
        msg.put("data", data);
        mqttService.sendMessage(JSONObject.toJSONString(msg), topic);
        log.info("向 " + topic + " 发送: " + data);
        return "OK";
    }

    /**
     * 获取消息
     */
    @GetMapping("/get")
    public String getMsg(@RequestParam("topic") String topic) {
        String cache = LocalCache.getCache(topic);
        if (StringUtils.isNotBlank(cache)) {
            LocalCache.removeCache(topic);
            return "OK " + cache;
        }
        return "OK 无缓存";
    }
}