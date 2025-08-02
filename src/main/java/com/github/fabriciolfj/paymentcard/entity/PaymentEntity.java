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
import java.util.Set;

@Entity
@Table(name = "payment")
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "card_token", nullable = false)
    private String cardToken;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "order_id", nullable = false)
    private String orderId;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "paymentEntity")
    private Set<PaymentLogsEntity> logs;
    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate;
    @Version
    @Column(name = "version")
    private Long version;
}
