package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.constants.PaymentColumnsConstants;
import com.github.fabriciolfj.paymentcard.model.PaymentSummary;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<PaymentSummary> {

    @Override
    public PaymentSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PaymentSummary.builder()
                .orderId(rs.getString(PaymentColumnsConstants.ORDER_ID))
                .customer(rs.getString(PaymentColumnsConstants.CUSTOMER_ID))
                .date(rs.getTimestamp(PaymentColumnsConstants.DATE).toLocalDateTime())
                .amount(rs.getBigDecimal(PaymentColumnsConstants.AMOUNT))
                .build();
    }
}
