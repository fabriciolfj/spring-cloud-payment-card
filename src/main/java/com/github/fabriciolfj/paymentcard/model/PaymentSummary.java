package com.github.fabriciolfj.paymentcard.model;

import com.github.fabriciolfj.paymentcard.clients.FraudResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentSummary {

    private LocalDateTime date;
    private BigDecimal amount;
    private String customer;
    private String orderId;
    private FraudResponse fraud;


    public PaymentSummary updateDataFraud(final FraudResponse fraud) {
        this.fraud = fraud;
        return this;
    }
}
