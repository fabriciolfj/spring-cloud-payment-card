package com.github.fabriciolfj.paymentcard.handler.exceptions;

import com.github.fabriciolfj.paymentcard.handler.enums.ErrorEnums;

public class CardTokenInvalidException extends RuntimeException {

    public CardTokenInvalidException() {
        super(ErrorEnums.CARD_TOKEN_EXCEPTION.getMessage());
    }
}
