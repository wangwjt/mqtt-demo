package com.example.mqttdemo.Tread;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * @description: 消息处理线程池(懒汉式)
 * @author: Okentao
 * @create: 2021-02-15 19:19
 */
public class MessageReceiveHandlerThreadPool {

    private static ExecutorService pool;

    /**
     * 线程数
     */
    private static int THREAD_NUM = 8;

    /**
     * 阻塞队列最大长度
     */
    private static int MAX_QUEUE_SIZE = 2000;

    private MessageReceiveHandlerThreadPool() {
    }

    /**
     * 接收task (双端检测)
     */
    public static void execute(Runnable task) {
        if (Objects.isNull(pool)) {
            synchronized (MessageReceiveHandlerThreadPool.class) {
                if (Objects.isNull(pool)) {
                    // 防止线程没处理完而持续接收消息
                    pool = new ThreadPoolExecutor(THREAD_NUM,
                            THREAD_NUM, 0L, TimeUnit.MILLISECONDS,
                            new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        pool.submit(task);
    }
}
