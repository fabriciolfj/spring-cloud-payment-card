package com.github.fabriciolfj.paymentcard.listener;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Log
public class FraudReturnListener {

    @Bean
    public Consumer<String> transactionProcessed() {
        return event -> log.info("receive event return fraud " + event);
    }
}
