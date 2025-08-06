package com.github.fabriciolfj.paymentcard.repositories;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
