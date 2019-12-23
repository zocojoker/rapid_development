package com.zoco.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * springTask任务定时器
 * task1是每隔5s执行一次，{秒} {分} {时} {日期} {月} {星期}
 * task2是延迟1s,每隔1S执行一次
 *
 * @author zoco
 * @creat 2019-12-18-11:43
 */
@Service
public class SpringTaskDemo {
    private static final Logger log = LoggerFactory.getLogger(SpringTaskDemo.class);

    //@Scheduled(cron = "1/1 * * * * *")
    public void task1() {
        System.out.println("开始spring-task定时任务1" + new Date());
        long i = 0;
        while (i < 10000000000l) {
            i++;
        }
        System.out.println("定时输出1:" + i + new Date());
    }

    @Scheduled(initialDelay = 1000, fixedRate = 1 * 1000)
    public void task2() {
        System.out.println("开始spring-task定时任务2" + new Date());
        long i = 0;
        while (i < 10000000000l) {
            i++;
        }
        System.out.println("定时输出2:" + i + new Date());
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext("/spring/spring-task.xml");
        /*SpringTaskDemo springTaskDemo = cpac.getBean(SpringTaskDemo.class);
        springTaskDemo.task1();*/
    }
}
