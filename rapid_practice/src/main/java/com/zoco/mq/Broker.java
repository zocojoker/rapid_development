package com.zoco.mq;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消息处理中心
 *
 * @author zoco
 * @creat 2019-12-20-11:12
 */
public class Broker {
    //设置存储消息的最大数量
    private final static int MAX_SIZE = 5;
    //保存消息的容器
    private static ArrayBlockingQueue<String> MassageQueue = new ArrayBlockingQueue<String>(MAX_SIZE);

    //生产消息
    public static void produce(String msg) {
        if (MassageQueue.offer(msg)) {
            System.out.println("成功向消息中心投递消息：" + msg + "，当前暂存消息数目为" + MassageQueue.size());
        } else {
            System.out.println("消息中心已满，不能继续放入消息！");
        }
        System.out.println("==================================");
    }

    //消费消息
    public static String consume() {
        String msg = MassageQueue.poll();
        if (msg != null) {
            System.out.println("已经消费消息：" + msg + "，当前暂存消息数目为" + MassageQueue.size());
        } else {
            System.out.println("消息处理中心已经没有消息可供消费！");
        }
        System.out.println("==================================");
        return msg;
    }
}
