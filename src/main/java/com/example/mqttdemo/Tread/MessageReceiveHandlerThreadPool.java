package com.example.mqttdemo.Tread;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 消息处理线程池(懒汉式)
 * @author: Okentao
 * @create: 2021-02-15 19:19
 */
public class MessageReceiveHandlerThreadPool {

    private static ExecutorService pool;

    private MessageReceiveHandlerThreadPool() {
    }

    /**
     * 接收task (双端检测)
     */
    public static void execute(Runnable task) {
        if (Objects.isNull(pool)) {
            synchronized (MessageReceiveHandlerThreadPool.class) {
                if (Objects.isNull(pool)) {
                    pool = Executors.newFixedThreadPool(4);
                }
            }
        }
        pool.submit(task);
    }
}
