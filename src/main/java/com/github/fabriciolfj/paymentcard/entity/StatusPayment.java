package com.github.fabriciolfj.paymentcard.entity;

import com.github.fabriciolfj.paymentcard.handler.exceptions.StatusPaymentNotFoundException;
import lombok.Getter;

import java.util.stream.Stream;

public enum StatusPayment {

    PENDING("peding", -1),
    APPROVED("approved", 100),
    REQUEST_CONFIRMATION("requestConfirmation", 50),
    DENIED("denied", 0);

    @Getter
    private String describe;
    private int score;

    StatusPayment(String describe, int score) {
        this.describe = describe;
        this.score = score;
    }

    public static StatusPayment toEnum(final String describe) {
        return Stream.of(StatusPayment.values())
                .filter(value -> value.describe.equals(describe))
                .findFirst()
                .orElseThrow(StatusPaymentNotFoundException::new);
    }

    public static StatusPayment toEnum(final int score) {
        return Stream.of(StatusPayment.values())
                .filter(value -> value.score == score)
                .findFirst()
                .orElseThrow(StatusPaymentNotFoundException::new);
    }

}
