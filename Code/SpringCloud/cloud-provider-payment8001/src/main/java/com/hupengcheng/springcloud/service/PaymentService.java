package com.hupengcheng.springcloud.service;

import com.hupengcheng.springcloud.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
