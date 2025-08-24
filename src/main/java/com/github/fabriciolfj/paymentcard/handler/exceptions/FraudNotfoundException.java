package com.github.fabriciolfj.paymentcard.handler.exceptions;

import com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums;

public class FraudNotfoundException extends RuntimeException {

    public FraudNotfoundException() {
        super(ErrorEnums.FRAUD_NOT_FOUNd.getMessage());
    }
}
