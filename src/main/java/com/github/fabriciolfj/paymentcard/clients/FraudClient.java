package com.github.fabriciolfj.paymentcard.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface FraudClient {

    @GetExchange("/{code}")
    FraudResponse findByCode(@PathVariable("code") final String code);
}
