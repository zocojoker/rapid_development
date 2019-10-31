package com.zoco.callable;

import java.util.concurrent.Callable;

/**
 * 测试多线程callable和future
 *
 * @author zoco
 * @creat 2019-06-14-14:54
 */
public class TestCallable implements Callable {
    private String msg;

    public TestCallable(String msg) {
        this.msg = msg;
    }

    public TestCallable() {
    }

    @Override
    public Object call() throws Exception {
        return "这个线程已经执行了" + msg;
    }

}
