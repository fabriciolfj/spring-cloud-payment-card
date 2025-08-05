package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.PaymentRepository;
import com.github.fabriciolfj.paymentcard.constants.QuerySQLConstants;
import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import com.github.fabriciolfj.paymentcard.exceptions.CardTokenInvalidException;
import com.github.fabriciolfj.paymentcard.exceptions.PaymentSaveException;
import com.github.fabriciolfj.paymentcard.model.PaymentSummary;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentService {

    private final ValidCardEncryptService validCardEncryptService;
    private final PaymentRepository repository;
    private final JdbcClient jdbcClient;

    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentEntity createPayment(@NonNull final PaymentEntity entity) {
        if (isTokeninvalid(entity)) {
            log.warn("token card invalid to payment {}", entity.getCode());
            throw new CardTokenInvalidException();
        }

        var result = savePayment(entity);
        log.info("payment saved {}", entity.getCode());
        return result;
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PaymentSummary findByCode(final String code) {
        var result = QuerySQLConstants.QUERY_SUMMARY_PAYMENT + code;
        log.info("query {}", result);

        return jdbcClient.sql(result).query(new PaymentRowMapper()).single();
    }

    private PaymentEntity savePayment(final PaymentEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            log.error("fail save payment {}, cause {}", entity.getCode(), e.getMessage());
            throw new PaymentSaveException();
        }
    }

    private boolean isTokeninvalid(final PaymentEntity entity) {
        return !validCardEncryptService.execute(entity.getCardToken());
    }
}
