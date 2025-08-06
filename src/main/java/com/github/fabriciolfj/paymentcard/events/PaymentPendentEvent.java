package com.github.fabriciolfj.paymentcard.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPendentEvent {

    private String code;
    private LocalDateTime date;
    private String customer;
    private String orderId;
    private BigDecimal amount;
}
