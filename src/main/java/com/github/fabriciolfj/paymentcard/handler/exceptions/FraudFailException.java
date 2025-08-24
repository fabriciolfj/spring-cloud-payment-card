package com.github.fabriciolfj.paymentcard.handler.exceptions;

import com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums;

public class FraudFailException extends RuntimeException {

    public FraudFailException() {
        super(ErrorEnums.FRAUD_ERROR.getMessage());
    }
}
