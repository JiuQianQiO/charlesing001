package com.zhi.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBanancer{
    /**
     *  编写一个原子类，初始值为0
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     *  得到并增加访问次数，定义两个变量；current：当前请求次数。  next：下一次请求对应的次数  因为每请求一次，在这里请求次数都需要加一
     * @return
     */
    public final int getAndIncrement(){

        int current;
        int next;
        do {
            // 获取当前请求次数
            current = this.atomicInteger.get();
            // 计算下一次请求次数
            next = current >= 2147483647 ? 0 : current + 1;
            //使用自旋锁，来判断当前的请求次数和下一次请求次数，符合才能结束，否则就一直自旋
//            在这里只要两个值不一样，才会结束
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("#################第几次访问，次数：next"+ next);
        return next;
    }


    /**
     *  获取服务器集群中，服务器的列表
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        // 获得当前那个服务应该服务     请求次数 %  集群中服务器的总数
         int index  = getAndIncrement() % serviceInstances.size();
          ServiceInstance serviceInstance = serviceInstances.get(index);
        // 返回应该 服务本次请求的服务器实例
        return serviceInstance;
    }
}
