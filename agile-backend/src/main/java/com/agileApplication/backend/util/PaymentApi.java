package com.agileApplication.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentApi {

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> payment(int amount, String currency) {
        String paymentUrl = "http://localhost:8082/api/payment/process?amount=" + amount + "&currency=" + currency;

        //  Call Payment Gateway API
        ResponseEntity<String> response = restTemplate.postForEntity(paymentUrl, null, String.class);

        return response;
    }
}
