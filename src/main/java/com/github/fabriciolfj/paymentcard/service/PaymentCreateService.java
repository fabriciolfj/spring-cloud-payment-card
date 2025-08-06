package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.handler.exceptions.PaymentNotFoundException;
import com.github.fabriciolfj.paymentcard.repositories.PaymentRepository;
import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.handler.exceptions.PaymentSaveException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentCreateService {

    private final PaymentNotificationService paymentNotificationService;
    private final PaymentValidationService paymentValidationService;
    private final PaymentRepository repository;

    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentEntity createPayment(final PaymentEntity entity) {
        return ofNullable(entity)
                .map(this::validatePayment)
                .map(this::save)
                .map(this::notifyPayment)
                .orElseThrow(PaymentNotFoundException::new);
    }

    private PaymentEntity validatePayment(final PaymentEntity entity) {
        var result = paymentValidationService.execute(entity);

        log.info("payment validate success {}", entity.getCode());
        return result;
    }

    private PaymentEntity notifyPayment(final PaymentEntity entity) {
        var result = paymentNotificationService.execute(entity);

        log.info("payment notification success {}", entity.getCode());
        return result;
    }

    private PaymentEntity save(final PaymentEntity entity) {
        try {
            final var result = repository.save(entity);

            log.info("payment saved {}", entity.getCode());
            return result;
        } catch (Exception e) {
            log.error("fail save payment {}, cause {}", entity.getCode(), e.getMessage());
            throw new PaymentSaveException();
        }
    }
}
