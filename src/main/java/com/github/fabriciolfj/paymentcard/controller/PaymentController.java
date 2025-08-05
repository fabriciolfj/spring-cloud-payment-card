package com.github.fabriciolfj.paymentcard.controller;

import com.github.fabriciolfj.paymentcard.dto.PaymentCreateResponse;
import com.github.fabriciolfj.paymentcard.dto.PaymentRequest;
import com.github.fabriciolfj.paymentcard.mapper.PaymentRequestMapper;
import com.github.fabriciolfj.paymentcard.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.github.fabriciolfj.paymentcard.mapper.PaymentRequestMapper.toEntity;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentCreateResponse createPayment(@Valid final PaymentRequest request) {
        var result = paymentService.createPayment(toEntity(request));

        return new PaymentCreateResponse(result.getCode());
    }
}
