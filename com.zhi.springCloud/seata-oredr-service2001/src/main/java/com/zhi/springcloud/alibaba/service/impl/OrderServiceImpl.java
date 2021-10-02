package com.zhi.springcloud.alibaba.service.impl;

import com.zhi.springcloud.alibaba.service.StorageService;
import com.zhi.springcloud.alibaba.dao.OrderDao;
import com.zhi.springcloud.alibaba.doamin.Order;
import com.zhi.springcloud.alibaba.service.AccountService;
import com.zhi.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService  accountService;



    @Override
    @GlobalTransactional(name = "seata_create_order",rollbackFor = Exception.class)
    // 在程序开始的入口处，写上@GlobalTransactional
    // name 是一个 @GlobalTransactional 注解的唯一标识，什么都可以，只要不重复
    // rollbackFor 是当程序发生了那些异常要进行回滚
    public void create(Order order) {
        log.info("------>  开始创建订单");
        //1、创建订单
        orderDao.create(order);

        //2、减库存
        log.info("------>  订单微服务开始调用库存，做扣减库存 Product---");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>  订单微服务开始调用库存，做扣减库存  Product---end");

        //3、减余额
        log.info("------>  订单微服务开始账户微服务，做余额扣减Money---");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------>  订单微服务开始账户微服务，做余额扣减Money--- end ");

        //4、修改订单状态 0 代表开始，1 代表结束，默认是结束
        log.info("------>$$$$$$$$  修改订单状态--- ");
        orderDao.update(order.getUserId(),0);
        log.info("------>$$$$$$$$  修改订单状态--- end ");


        log.info("******下订单结束******");
    }
}
