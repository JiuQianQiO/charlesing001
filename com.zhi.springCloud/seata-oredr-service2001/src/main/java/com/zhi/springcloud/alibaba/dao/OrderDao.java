package com.zhi.springcloud.alibaba.dao;


import com.zhi.springcloud.alibaba.doamin.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    // 1、新建订单
    void create(Order order);

    // 2、修改订单状态，从0改成1

    void update(@Param("userId") Long userId, @Param("status") Integer status);


}
