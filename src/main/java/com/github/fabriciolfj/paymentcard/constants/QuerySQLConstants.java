package com.github.fabriciolfj.paymentcard.constants;

public class QuerySQLConstants {

    public static String QUERY_SUMMARY_PAYMENT = "select amount, customer_id, order_id, date_create from payment where code = ";

    private QuerySQLConstants() { }
}
