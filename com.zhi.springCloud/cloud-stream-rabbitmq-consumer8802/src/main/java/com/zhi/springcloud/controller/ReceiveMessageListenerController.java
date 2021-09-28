package com.zhi.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class) //定义消息接受者的管道  ，接受消息的管道
@Component
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)  //  sink 接受消息，下面的 Message<String> message 就是消息生产者 build  后是消息
    public  void  input(Message<String> message){
        System.out.println("消费者 1 号，-----》 接受的消息：" + message.getPayload() + "\t, 端口号：  "+ serverPort);
    }
}
