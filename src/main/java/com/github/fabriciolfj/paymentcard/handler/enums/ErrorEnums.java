package com.github.fabriciolfj.paymentcard.handler.enums;

import java.util.ResourceBundle;

public enum ErrorEnums {

    FRAUD_NOT_FOUNd,
    FRAUD_ERROR,
    CARD_TOKEN_EXCEPTION,
    STATUS_PAYMENT_EXCEPTION,
    NOTIFICATION_EXCEPTION,
    PAYMENT_NOT_FOUND,
    PAYMENT_SAVE_EXCEPTION;

    public String getMessage() {
        var bundle = ResourceBundle.getBundle("exceptions/messages");
        return bundle.getString(this.name() + ".message");
    }
}
