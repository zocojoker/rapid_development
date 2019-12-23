package com.zoco.objecttest;

/**
 * 测试finalize自我拯救
 *
 * @author zoco
 * @creat 2019-11-15-9:46
 */
public class TestFinalize {
    public static TestFinalize TEST_FINAL;

    public static void main(String[] args) throws InterruptedException {
        TEST_FINAL = new TestFinalize();
        TEST_FINAL = null;
        System.gc();
        Thread.sleep(500);
        if (null != TEST_FINAL) {
            System.out.println("I was deaded");
        } else {
            System.out.println("Yes,I`m still alive");
        }
        TEST_FINAL = null;
        System.gc();
        Thread.sleep(500);
        if (null != TEST_FINAL) {
            System.out.println("I was deaded");
        } else {
            System.out.println("Yes,I`m still alive");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Excute Finalize Method");
        TEST_FINAL = this;
    }
}
