package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.handler.exceptions.PaymentNotFoundException;
import com.github.fabriciolfj.paymentcard.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindPaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentEntity execute(final String code) {
        return paymentRepository.findByCode(code)
                .orElseThrow(PaymentNotFoundException::new);
    }
}
