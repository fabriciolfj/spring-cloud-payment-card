package com.github.fabriciolfj.paymentcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class PaymentCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentCardApplication.class, args);
	}

}
