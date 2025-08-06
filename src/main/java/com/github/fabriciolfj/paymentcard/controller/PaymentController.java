package com.github.fabriciolfj.paymentcard.controller;

import com.github.fabriciolfj.paymentcard.dto.PaymentCreateResponse;
import com.github.fabriciolfj.paymentcard.dto.PaymentRequest;
import com.github.fabriciolfj.paymentcard.model.PaymentSummary;
import com.github.fabriciolfj.paymentcard.service.FindPaymentService;
import com.github.fabriciolfj.paymentcard.service.PaymentCreateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.fabriciolfj.paymentcard.mapper.PaymentRequestMapper.toEntity;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentCreateService paymentCreateService;
    private final FindPaymentService findPaymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentCreateResponse createPayment(@Valid @RequestBody final PaymentRequest request) {
        log.info("request received {}", request);
        var result = paymentCreateService.createPayment(toEntity(request));

        return new PaymentCreateResponse(result.getCode());
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentSummary getPayment(@PathVariable @NotNull final String code) {
        log.info("code received {}", code);
        return findPaymentService.findByCode(code);
    }
}
