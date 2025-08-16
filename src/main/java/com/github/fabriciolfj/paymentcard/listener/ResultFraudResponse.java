package com.github.fabriciolfj.paymentcard.listener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultFraudResponse {

    private String code;
    private int score;
}
