package com.github.fabriciolfj.paymentcard.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.paymentcard.service.UpdateStatusPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
@Log
public class FraudReturnListener {

    private final UpdateStatusPaymentService updateStatusPaymentService;
    private final ObjectMapper objectMapper;

    @Bean
    public Consumer<String> transactionProcessed() {
        return event -> {
            log.info("receive event return fraud " + event);
            try {
                var result = objectMapper.readValue(event, ResultFraudResponse.class);

                updateStatusPaymentService.execute(result.getCode(), result.getScore());
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }
}
