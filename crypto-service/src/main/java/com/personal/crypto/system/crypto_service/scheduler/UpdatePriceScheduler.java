package com.personal.crypto.system.crypto_service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class UpdatePriceScheduler {

    private final String binanceUrl;
    private final String houbiUrl;

    @Autowired
    private RestTemplate restTemplate;

    // constructor-based dependency injection
    public UpdatePriceScheduler(Environment env) {
        this.binanceUrl = env.getProperty("crypto.api.binance.url");
        this.houbiUrl = env.getProperty("crypto.api.huobi.url");
    }
    
    @Scheduled(fixedRate = 10000) // 1000 = 1 sec
    public void fetchAndStoreBestPrice() {
        // call source API
        // compare bid and ask prices
        // store the best one into AggregatedPrice table
        try {
            ResponseEntity<String> response = restTemplate.exchange(binanceUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
            log.info("Binance connected successfully");
        }
        catch (RestClientException e) {
            log.atError().withThrowable(e).log("Binance api call failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        try {
            ResponseEntity<String> response = restTemplate.exchange(houbiUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
            log.info("Houbi connected successfully");
        }
        catch (RestClientException e) {
            log.atError().withThrowable(e).log("Houbi api call failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
