package com.zoco.mq;

/**
 * 消费者客户端测试类
 *
 * @author zoco
 * @creat 2019-12-20-11:17
 */
public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        MQClient client = new MQClient();
        String message = client.consume();
        System.out.println("获取的消息为：" + message);
    }
}
