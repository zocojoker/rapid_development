package com.zoco.timer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * 和spring整合
 *
 * @author zoco
 * @creat 2019-12-18-15:01
 */
public class QuartzXmlDemo {
    public void first() {
        System.out.println("QuartzDemo:" + new Date() + ":Start！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("QuartzDemo:" + new Date() + ":End！");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("spring/spring-quartz.xml");
        /*SpringTaskDemo springTaskDemo = cpac.getBean(SpringTaskDemo.class);
        springTaskDemo.task1();*/
    }
}
