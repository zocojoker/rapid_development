package com.zoco.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zoco
 * @creat 2019-06-14-14:57
 */
public class TestThreadPool {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        for (int i = 0; i < 20; i++) {
            TestCallable callable = new TestCallable(i + "00");
            FutureTask<String> task = new FutureTask<String>(callable);
            threadPoolExecutor.submit(task);
            try {
                list.add(task.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(list.size());
        for(String s:list){
            System.out.println(s);
        }


        for (int i = 0; i < 20; i++) {
            TestCallable callable = new TestCallable(i + "00");
            Future<String> future = threadPoolExecutor.submit(callable);
            try {
                list.add(future.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(list.size());
        for(String s:list){
            System.out.println(s);
        }
        ExecutorService  es = Executors.newFixedThreadPool(10);
    }
}
