package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.clients.FraudClient;
import com.github.fabriciolfj.paymentcard.constants.QuerySQLConstants;
import com.github.fabriciolfj.paymentcard.handler.exceptions.PaymentNotFoundException;
import com.github.fabriciolfj.paymentcard.model.PaymentSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindSummaryPaymentService {

    private final JdbcClient jdbcClient;
    private final FraudClient fraudClient;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PaymentSummary findByCode(final String code) {
        var result = QuerySQLConstants.QUERY_SUMMARY_PAYMENT;
        log.info("query {}", result);

        var payment = jdbcClient.sql(result)
                .param(code)
                .query(new PaymentRowMapper())
                .optional();

        if (payment.isEmpty()) {
            throw new PaymentNotFoundException();
        }

        var fraud = fraudClient.findByCode(code);
        return payment.get().updateDataFraud(fraud);
    }
}
