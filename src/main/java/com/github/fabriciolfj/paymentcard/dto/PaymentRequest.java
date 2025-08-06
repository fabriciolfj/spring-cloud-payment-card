package com.github.fabriciolfj.paymentcard.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    @NotNull(message = "{payment.amount.required}")
    @Positive(message = "{payment.amount.positive}")
    @DecimalMin(value = "0.01", message = "{payment.amount.min}")
    @DecimalMax(value = "999999.99", message = "{payment.amount.max}")
    private BigDecimal amount;

    @NotBlank(message = "{payment.cardToken.required}")
    @Size(min = 10, max = 100, message = "{payment.cardToken.size}")
    @Pattern(regexp = "^[A-Za-z0-9+/=]+$", message = "{payment.cardToken.pattern}")
    private String cardToken;

    @NotBlank(message = "{payment.customerId.required}")
    @Size(min = 1, max = 50, message = "{payment.customerId.size}")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{payment.customerId.pattern}")
    private String customerId;

    @NotBlank(message = "{payment.orderId.required}")
    @Size(min = 1, max = 50, message = "{payment.orderId.size}")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "{payment.orderId.pattern}")
    private String orderId;
}
