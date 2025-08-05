package com.github.fabriciolfj.paymentcard.model;

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

    private String code;
    private LocalDateTime date;
    private BigDecimal amount;
    private String customer;
    private String orderId;
}
