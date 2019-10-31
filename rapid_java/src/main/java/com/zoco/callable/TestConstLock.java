package com.zoco.callable;

/**
 * @author zoco
 * @creat 2019-06-27-17:32
 */
public class TestConstLock implements Runnable {
    private Object lock;

    public TestConstLock(Object lock) {
        super();
        this.lock = lock;
    }

    public void run() {
        synchronized (lock) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "sayHello");
            }
        }
    }

    public static void main(String[] args) {
        TestConstLock constLock1 = new TestConstLock("lock");
        TestConstLock constLock2 = new TestConstLock(new String("lock"));
        Thread t1 = new Thread(constLock1, "Thread1");
        Thread t2 = new Thread(constLock2, "Thread2");
        t1.start();
        t2.start();
    }

   /* public static void main(String[] args) {
        TestConstLock constLock1 = new TestConstLock(21);
        TestConstLock constLock2 = new TestConstLock(21);
        Thread t1 = new Thread(constLock1, "Thread1");
        Thread t2 = new Thread(constLock2, "Thread2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        TestConstLock constLock1 = new TestConstLock(21l);
        TestConstLock constLock2 = new TestConstLock(21l);
        Thread t1 = new Thread(constLock1, "Thread1");
        Thread t2 = new Thread(constLock2, "Thread2");
        t1.start();
        t2.start();
    }*/

}
