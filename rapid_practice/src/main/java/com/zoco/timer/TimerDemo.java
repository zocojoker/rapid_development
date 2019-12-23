package com.zoco.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer定时任务
 *
 * @author zoco
 * @creat 2019-12-18-10:39
 * timer有2中方法schedule和scheduleAtFixedRate，前者会等任务结束在开始计算时间间隔，后者是在任务开始就计算时间，有并发的情况
 */
public class TimerDemo {
    public static void main(String[] args) throws ParseException, InterruptedException {
        schedule(1000, 2000);
//        scheduleAtFixedRate(1000, 2000);
    }

    public static void schedule(long delay, long period) throws InterruptedException {
        Timer timer = new Timer("zoco_timer");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                int i = 0;
                while (i < 10000) {
                    i++;
                }
                System.out.println("timer定时输出:" + new Date());
            }
        }, delay, period);//1000ms是延迟启动时间，2000ms是定时任务周期，每2s执行一次
        Thread.sleep(20000);
        System.out.println("取消定时任务");
        timer.cancel();
    }

    /**
     * 测试感觉还是串行运行的，需要等待上一个任务结束下一个任务才能开始运行
     *
     * @param delay
     * @param period
     * @throws ParseException
     */
    public static void scheduleAtFixedRate(long delay, long period) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = dateFormat.parse("2019-12-18 17:23:40.000");
        new Timer("zoco_timer").scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                /*try {
                    System.out.println("开始定时任务" + new Date());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("开始timer定时任务" + new Date());
                long i = 0;
                while (i < 10000000000l) {
                    i++;
                }
                System.out.println("timer定时输出:" + i + new Date());
            }
        }, date, period);//date是开始时间，2000ms是定时任务周期，每2s执行一次
    }
}
