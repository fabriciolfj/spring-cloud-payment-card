package com.github.fabriciolfj.paymentcard.handler.exceptions;

import com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums;

public class StatusPaymentNotFoundException extends RuntimeException {

    public StatusPaymentNotFoundException() {
        super(ErrorEnums.STATUS_PAYMENT_EXCEPTION.getMessage());
    }
}
