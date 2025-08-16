package com.github.fabriciolfj.paymentcard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "payment_logs")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Version
    @Column(name = "version")
    private Long version;
}
