package com.study.springcloud.service;

import com.study.springcloud.pojo.Payment;
import com.study.springcloud.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentImpl implements PaymentService {

    @Value("${server.port}")
    String port;

    @Autowired
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment queryById(long id) {
        return paymentDao.queryById(id);
    }

    @Override
    public String getPort() {
        return port;
    }
}