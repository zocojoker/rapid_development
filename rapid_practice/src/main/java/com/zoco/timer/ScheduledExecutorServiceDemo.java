package com.zoco.timer;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * ScheduledExecutorService定时任务
 *
 * @author zoco
 * @creat 2019-12-18-10:51
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) throws ParseException {
        ScheduledExecutorService scheduledExecutorService = new ScheduledExecutorService() {
            @Override
            public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
                Thread thread = new Thread(command);
                thread.start();
                return null;
            }

            @Override
            public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
                Thread thread = new Thread(command);
                thread.start();
                return null;
            }

            @Override
            public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
                return null;
            }

            @Override
            public void shutdown() {

            }

            @Override
            public List<Runnable> shutdownNow() {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> Future<T> submit(Callable<T> task) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Runnable task, T result) {
                return null;
            }

            @Override
            public Future<?> submit(Runnable task) {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void execute(Runnable command) {

            }
        };
//        schedule(scheduledExecutorService);
        scheduleAtFixedRate(1, 1);
//        scheduleWithFixedDelay(1, 1);
    }

    public static void schedule(ScheduledExecutorService scheduledExecutorService) {

        scheduledExecutorService.schedule(() -> System.out.println("ScheduledTask" + new Date()), 1, TimeUnit.SECONDS);//延迟1s启动，执行一次
    }

    public static void scheduleAtFixedRate(long delay, long period) throws ParseException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("开始ScheduledExecutorService定时任务" + new Date());
            long i = 0;
            /*while (i < 10000000000l) {
                i++;
            }*/
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("定时输出:" + i + new Date());
        }, delay, period, TimeUnit.SECONDS);//延迟1s启动，每隔1s执行一次，是前一个任务开始时就开始计算时间间隔，但是会等上一个任务结束在开始下一个
    }

    public static void scheduleWithFixedDelay(long delay, long period) throws ParseException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("开始ScheduledExecutorService定时任务" + new Date());
            long i = 0;
            while (i < 10000000000l) {
                i++;
            }
            System.out.println("定时输出:" + i + new Date());
        }, 1, 1, TimeUnit.SECONDS);//延迟1s启动，在前一个任务执行完成之后，延迟1s在执行
    }
}
