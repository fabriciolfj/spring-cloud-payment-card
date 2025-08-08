package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.events.PaymentPendentEvent;
import com.github.fabriciolfj.paymentcard.handler.exceptions.NotificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.github.fabriciolfj.paymentcard.constants.HeaderNotificationConstants.CORRELATION_ID;
import static com.github.fabriciolfj.paymentcard.mapper.PaymentPendentMapper.toEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentNotificationService {

    @Value("${topic.process-fraud}")
    private String topic;
    private final StreamBridge streamBridge;

    public PaymentEntity execute(final PaymentEntity entity) {
        try {
            final Message<PaymentPendentEvent> message = MessageBuilder
                    .withPayload(toEvent(entity))
                    .setHeader(CORRELATION_ID, UUID.randomUUID().toString())
                    .build();

            streamBridge.send(topic, message);
            return entity;
        } catch (Exception e) {
            log.error("fail notify payment {}, details {}", entity.getCode(), e.getMessage());
            throw new NotificationException();
        }
    }
}
