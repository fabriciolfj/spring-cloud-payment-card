package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.handler.exceptions.CardTokenInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentValidationService {

    private final ValidCardEncryptService validCardEncryptService;

    public PaymentEntity execute(final PaymentEntity entity) {
        if (isTokeninvalid(entity)) {
            log.warn("token card invalid to payment {}", entity.getCode());
            throw new CardTokenInvalidException();
        }

        return entity;
    }

    private boolean isTokeninvalid(final PaymentEntity entity) {
        return !validCardEncryptService.execute(entity.getCardToken());
    }
}
