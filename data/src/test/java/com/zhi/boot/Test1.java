package com.zhi.boot;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@DisplayName("测试类的名称")
@Tag("myTest")
@SpringBootTest
public class Test1 {

    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试开始");
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("单个测试开始");
    }

    @Test
    @RepeatedTest(3)
    void test1(){

        System.out.println("这个方法是重复执行");
    }
    @Test
    @Timeout(value = 500,unit = TimeUnit.MILLISECONDS)
    void test2() throws InterruptedException {
        Thread.sleep(600);
    }

    @Test
    @Disabled
    void tes3(){
        System.out.println("表示这个方法暂停使用");
    }

    @AfterEach
    void  testAfterEach(){
        System.out.println("单个测试结束");
    }
    @AfterAll
    static void  testAfterAll(){
        System.out.println("所有测试结束");
    }
}
