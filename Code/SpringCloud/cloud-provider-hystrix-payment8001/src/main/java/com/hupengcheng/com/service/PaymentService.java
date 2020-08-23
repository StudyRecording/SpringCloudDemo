package com.hupengcheng.com.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id + "\t" + "哈哈～";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {

        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // int age = 10/0;
        return "线程池：" + Thread.currentThread().getName() + "  paymentInfo_TimeOut,id:  " + id + "\t" + "呜呜～";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " 8001系统繁忙或程序错误，请稍后重试！, id: " + id + "\t" + "o(T__T)o";
    }
}
