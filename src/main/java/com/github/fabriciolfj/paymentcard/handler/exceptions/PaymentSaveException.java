package com.github.fabriciolfj.paymentcard.handler.exceptions;

import com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums;

public class PaymentSaveException extends RuntimeException {

    public PaymentSaveException() {
        super(ErrorEnums.PAYMENT_SAVE_EXCEPTION.getMessage());
    }
}
