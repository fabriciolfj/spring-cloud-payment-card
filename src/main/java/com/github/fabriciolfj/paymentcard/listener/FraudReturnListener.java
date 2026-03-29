package com.github.fabriciolfj.paymentcard.listener;

import com.github.fabriciolfj.paymentcard.service.UpdateStatusPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
@Log
public class FraudReturnListener {

    private final UpdateStatusPaymentService updateStatusPaymentService;
    private final JsonMapper jsonMapper;

    @Bean
    public Consumer<String> transactionProcessed() {
        return event -> {
            log.info("receive event return fraud " + event);
            try {
                var result = jsonMapper.readValue(event, ResultFraudResponse.class);

                updateStatusPaymentService.execute(result.getCode(), result.getScore());
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        };
    }
}
