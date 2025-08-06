package com.github.fabriciolfj.paymentcard.mapper;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.events.PaymentPendentEvent;

public class PaymentPendentMapper {

    private PaymentPendentMapper() { }

    public static PaymentPendentEvent toEvent(final PaymentEntity entity) {
        return PaymentPendentEvent.builder()
                .orderId(entity.getOrderId())
                .code(entity.getCode())
                .date(entity.getDateCreate())
                .amount(entity.getAmount())
                .customer(entity.getCustomerId())
                .build();
    }
}
