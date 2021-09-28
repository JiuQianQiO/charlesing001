package com.zhi.springcloud.impl;

import com.zhi.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class) // 定义消息发送者的生产管道 或者 定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        // 定义消息绑定器
        Message<String> build = MessageBuilder.withPayload(serial).build();
        //发送消息
        output.send(build);
        System.out.println("*********serial***:   "+serial);
        return null;
    }
}
