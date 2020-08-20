package com.hupengcheng.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class MyLB implements LoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
            if (atomicInteger.compareAndSet(current, next)) {
                System.out.println("****访问次数next" + next);
                return next;
            }
        } while(true);
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

}
