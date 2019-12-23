package com.zoco.spi.impl;

import com.zoco.spi.ISPIService;

/**
 * @author zoco
 * @creat 2019-11-13-11:24
 */
public class ServiceSPI1 implements ISPIService {
    @Override
    public void testSPI() {
        System.out.println("这是第一个实现");
    }
}
