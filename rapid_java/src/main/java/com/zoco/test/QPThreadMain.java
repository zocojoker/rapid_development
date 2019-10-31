package com.zoco.test;

/**
 * 继承thread抢票
 *
 * @author zoco
 * @creat 2019-01-03-10:37
 */
public class QPThreadMain {
    public static void main(String[] args) {
        /*try {
            Thread t1 = new QPThread();
            Thread t2 = new QPThread();
            Thread t3 = new QPThread();
            t1.setName("一号窗口");
            t2.setName("二号窗口");
            t3.setName("三号窗口");
            t1.start();
            t2.start();
            t3.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        QPRunnable t = new QPRunnable();//只能使用同一个t
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.setName("一号窗口");
        t2.setName("二号窗口");
        t3.setName("三号窗口");
        t1.start();
        t2.start();
        t3.start();
    }
}

class QPThread extends Thread {
    public static int tickets = 100;
    static String lock = "lock";

    //private int tickets = 100;//thread私有变量线程不共享
    @Override
    public void run() {

        while (tickets > 0) {
            synchronized (lock) {
                try {
                    if (tickets <= 0) break;
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + tickets + "张票");
                    --tickets;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

class QPRunnable implements Runnable {

    private int tickets = 100;//Runnable变量线程共享，因为他只生成了一个对象

    @Override
    public void run() {
        while (tickets > 0) {
            synchronized (this) {
                try {
                    if (tickets <= 0) break;
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + "卖出了第" + tickets + "张票");
                    --tickets;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
