package com.github.fabriciolfj.paymentcard.entity;

import com.github.fabriciolfj.paymentcard.handler.exceptions.StatusPaymentNotFoundException;

import java.util.stream.Stream;

public enum StatusPayment {

    PENDING("peding"),
    APPROVED("approved"),
    REQUEST_CONFIRMATION("requestConfirmation"),
    DENIED("denied");

    private String describe;

    StatusPayment(String describe) {
        this.describe = describe;
    }

    public static StatusPayment toEnum(final String describe) {
        return Stream.of(StatusPayment.values())
                .filter(value -> value.describe.equals(describe))
                .findFirst()
                .orElseThrow(StatusPaymentNotFoundException::new);
    }

    public String getDescribe() {
        return describe;
    }
}
