package com.zhi.boot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class test2 {

    @Test
    void testAssertEquals(){
        int call = call(3, 3);
        Assertions.assertEquals(6,call,"数值计算错误");
        Assertions.assertArrayEquals(new int[]{1,2,3},new int[]{1,2,3},"两个数组不相等");
        Assertions.assertAll("head",
                () -> Assertions.assertEquals(2,2),
                () -> Assertions.assertTrue( true && true)
        );

    }

    @Test
    void testException(){
        //断言业务逻辑一定会出现异常
        Assertions.assertThrows(ArithmeticException.class,
                () ->{int i = 10/0;},
                "异常断言失败");
    }

    int call(int i,int j){
       return i + j;
    }


    @DisplayName("参数化测试，参数是数字")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    void testParam(int i){
        System.out.println(i);
    }

    @DisplayName("参数化测试，参数是字符串")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4","apple","banana"})
    void testParam2(String i){
        System.out.println(i);
    }


    @DisplayName("参数化测试,参数是方法")
    @ParameterizedTest
   @MethodSource("stringProvider")
    void testParam3(String i){
        System.out.println(i);
    }

    static Stream<String> stringProvider(){
        return Stream.of("apple","one","two","three");
    }
}
