package com.zoco.mq;

/**
 * 生产者客户端测试类
 *
 * @author zoco
 * @creat 2019-12-20-11:17
 */
public class ProduceClient {
    public static void main(String[] args) throws Exception {
        MQClient client = new MQClient();
        client.produce("Hello World4!!");
    }
}
