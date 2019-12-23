package com.zoco.spi.impl;

import com.zoco.spi.ISPIService;
import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zoco
 * @creat 2019-11-13-11:27
 */
public class TestSPI {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Iterator<ISPIService> providers = Service.providers(ISPIService.class);
        ServiceLoader<ISPIService> load = ServiceLoader.load(ISPIService.class);

        while (providers.hasNext()) {
            ISPIService ser = providers.next();
            ser.testSPI();
        }
        System.out.println("--------------------------------");
        Iterator<ISPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            ISPIService ser = iterator.next();
            ser.testSPI();
        }


        /*Class<?> ispiService = Class.forName("com.zoco.spi.impl.ServiceSPI2");
        ISPIService ispiService1 = (ISPIService) ispiService.newInstance();
        ispiService1.testSPI();*/
    }
}
