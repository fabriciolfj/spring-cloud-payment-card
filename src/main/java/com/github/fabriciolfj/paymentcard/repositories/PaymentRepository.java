package com.github.fabriciolfj.paymentcard.repositories;

import com.github.fabriciolfj.paymentcard.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p FROM PaymentEntity p JOIN FETCH p.logs WHERE p.code = :code")
    Optional<PaymentEntity> findByCode(@Param("code") final String code);
}
