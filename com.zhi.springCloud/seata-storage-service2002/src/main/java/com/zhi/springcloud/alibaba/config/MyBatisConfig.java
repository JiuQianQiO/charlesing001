package com.zhi.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.zhi.springcloud.alibaba.dao"})
public class MyBatisConfig {

}
