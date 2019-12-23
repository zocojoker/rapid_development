package com.zoco.timer;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * quartz定时器
 *
 * @author zoco
 * @creat 2019-12-18-14:51
 */
public class QuartzDemo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("QuartzDemo:" + new Date() + ":Start！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("QuartzDemo:" + new Date() + ":End！");
    }

    public static void main(String[] args) throws SchedulerException {
        //创建调度器Schedule
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //创建JobDetail实例，并与HelloWordlJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(QuartzDemo.class).withIdentity("job1", "jobGroup1")
                .build();
        //创建触发器Trigger实例(立即执行，每隔1S执行一次)
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        //开始执行
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
