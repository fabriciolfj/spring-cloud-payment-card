package com.github.fabriciolfj.paymentcard.handler.exceptions;

import static com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums.NOTIFICATION_EXCEPTION;

public class NotificationException extends RuntimeException {

    public NotificationException() {
        super(NOTIFICATION_EXCEPTION.getMessage());
    }
}
