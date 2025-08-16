package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.entity.StatusPayment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.github.fabriciolfj.paymentcard.mapper.PaymentLogsEntityMapper.toEntity;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateStatusPaymentService {

    private final FindPaymentService findPaymentService;
    private final PaymentCreateService paymentCreateService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void execute(final String code, final int score) {
        var payment = findPaymentService.execute(code);
        payment.addLogs(toEntity(StatusPayment.toEnum(score)));

        paymentCreateService.save(payment);
        log.info("payment update success {}, logs {}", code, payment.getLogs());
    }
}
