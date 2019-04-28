package com.netease.dubbo;

import com.netease.dubbo.service.EchoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yangshaokai on 2018/2/7.
 */
public class ConsumerTest {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerTest.class);
    EchoService echoService;

    @Before
    public void before() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        echoService = (EchoService) context.getBean("echoService", EchoService.class);
    }

    @Test
    public void test() {
        System.out.println("test call~~");
        String result = echoService.echo();
        System.out.println("test call end ~~:" + result);
    }

    @Test
    public void testTimeout() {
        System.out.println("test timeout call~~");
        int i = 0;
        while (++i < 50) {
            long begin = System.currentTimeMillis();
            String result = echoService.echoWithTimeOut();
            long end = System.currentTimeMillis();
            System.out.println("test timeout call end ~~:" + result + ", cost:" + (end - begin) + "ms");
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testEchoException() {
        System.out.println("test exception call~~");
        echoService.echoWithException();
        System.out.println("test exception call end~~");
    }
}
