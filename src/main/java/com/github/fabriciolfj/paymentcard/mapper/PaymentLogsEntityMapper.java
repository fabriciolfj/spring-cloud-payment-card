package com.github.fabriciolfj.paymentcard.mapper;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.entity.PaymentLogsEntity;
import com.github.fabriciolfj.paymentcard.entity.StatusPayment;

public class PaymentLogsEntityMapper {

    private PaymentLogsEntityMapper() { }

    public static PaymentLogsEntity toEntity(final PaymentEntity entity, final StatusPayment status) {
        return PaymentLogsEntity
                .builder()
                .paymentEntity(entity)
                .status(status)
                .build();
    }
}
