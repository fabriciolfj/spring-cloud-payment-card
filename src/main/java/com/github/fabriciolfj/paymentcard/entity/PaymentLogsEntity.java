package com.github.fabriciolfj.paymentcard.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_logs")
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentLogsEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;
    @Version
    @Column(name = "version")
    private Long version;
}
