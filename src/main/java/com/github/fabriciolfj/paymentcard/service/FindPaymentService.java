package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.constants.QuerySQLConstants;
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
public class FindPaymentService {

    private final JdbcClient jdbcClient;

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PaymentSummary findByCode(final String code) {
        var result = QuerySQLConstants.QUERY_SUMMARY_PAYMENT;
        log.info("query {}", result);

        return jdbcClient.sql(result)
                .param(code)
                .query(new PaymentRowMapper())
                .single();
    }
}
