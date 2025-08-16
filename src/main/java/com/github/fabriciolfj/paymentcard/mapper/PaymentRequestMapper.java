package com.github.fabriciolfj.paymentcard.mapper;

import com.github.fabriciolfj.paymentcard.dto.PaymentRequest;
import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.entity.StatusPayment;

import java.util.UUID;

public class PaymentRequestMapper {

    private PaymentRequestMapper() { }

    public static PaymentEntity toEntity(final PaymentRequest dto) {
        var entity = PaymentEntity.builder()
                .code(UUID.randomUUID().toString())
                .amount(dto.getAmount())
                .cardToken(dto.getCardToken())
                .orderId(dto.getOrderId())
                .customerId(dto.getCustomerId())
                .build();

        entity.addLogs(PaymentLogsEntityMapper.toEntity(StatusPayment.PENDING));
        return entity;
    }
}
