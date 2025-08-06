package com.github.fabriciolfj.paymentcard.handler.exceptions;

import static com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums.PAYMENT_NOT_FOUND;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException() {
        super(PAYMENT_NOT_FOUND.getMessage());
    }
}
