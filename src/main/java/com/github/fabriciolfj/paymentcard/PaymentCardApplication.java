package com.github.fabriciolfj.paymentcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.github.fabriciolfj.paymentcard.repositories")
@EntityScan(basePackages = "com.github.fabriciolfj.paymentcard.entity")
@SpringBootApplication
@EnableConfigurationProperties
public class PaymentCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentCardApplication.class, args);
	}

}
