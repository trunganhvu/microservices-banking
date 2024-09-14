package com.anhvt.paymentprocessingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.anhvt.paymentprocessingservice", "com.anhvt.commonservice"})
public class PaymentprocessingserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentprocessingserviceApplication.class, args);
    }

}
