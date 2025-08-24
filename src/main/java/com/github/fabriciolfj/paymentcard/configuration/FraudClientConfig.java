package com.github.fabriciolfj.paymentcard.configuration;

import com.github.fabriciolfj.paymentcard.clients.FraudClient;
import com.github.fabriciolfj.paymentcard.handler.exceptions.FraudFailException;
import com.github.fabriciolfj.paymentcard.handler.exceptions.FraudNotfoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
@Slf4j
public class FraudClientConfig {

    @Value("${fraud.client.url}")
    private String url;

    @Bean
    public FraudClient restClient() {
        var client = RestClient.builder().baseUrl(url)
                .requestFactory(clientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, this::handle4xxError)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, this::handle4xxError)
                .build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();

        return factory.createClient(FraudClient.class);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(10));
        factory.setReadTimeout(Duration.ofSeconds(10));

        return factory;
    }

    private void handle4xxError(final HttpRequest request, final ClientHttpResponse response) throws IOException {
        var body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

        log.info("fail request fraud {}, details {}", request.getURI(), body);

        switch (response.getStatusCode().value()) {
            case 400, 422, 401, 403, 429, 404 -> throw new FraudNotfoundException();
            default -> throw new FraudFailException();
        }
    }
}
