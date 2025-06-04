package com.personal.crypto.system.crypto_service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.crypto.system.crypto_service.model.AggregatedPrice;
import com.personal.crypto.system.crypto_service.model.Binance;
import com.personal.crypto.system.crypto_service.model.Houbi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        // Fetch from binance & houbi
        List<AggregatedPrice> binanceData = fetchFromBinance();
        List<AggregatedPrice> houbiData = fetchFromHoubi();

        List<AggregatedPrice> allNewPricing = new ArrayList<>();
        allNewPricing.addAll(binanceData);
        allNewPricing.addAll(houbiData);

        // Retrieve from db
        // compare bid and ask prices
        // store the best one into AggregatedPrice table
    }

    private List<AggregatedPrice> fetchFromBinance() {
        List<AggregatedPrice> filteredBinance = new ArrayList<>();
        try {
            ResponseEntity<String> binanceResponse = restTemplate.exchange(binanceUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
            log.info("Binance connected successfully");
            ObjectMapper mapper = new ObjectMapper();
            Binance[] binanceData = mapper.readValue(binanceResponse.getBody(), Binance[].class);
            // Filter each data that matches btcusdt or ethusdt, from the filtered data, create AggregatedPrice object
            filteredBinance = Arrays.stream(binanceData)
                .filter(data -> "btcusdt".equals(data.getSymbol().toLowerCase()) || "ethusdt".equals(data.getSymbol().toLowerCase()))
                .map(filteredData -> new AggregatedPrice(filteredData.getSymbol(), filteredData.getBidPrice(), filteredData.getAskPrice(), "binance", LocalDateTime.now()))
                .collect(Collectors.toList());

            log.info("Successfully updated latest price from Binance");
        }
        catch (RestClientException e) {
            log.atError().withThrowable(e).log("Binance api call failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (JsonProcessingException e) {
            log.atError().withThrowable(e).log("Binance api call json processing failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return filteredBinance;
    }

    private List<AggregatedPrice> fetchFromHoubi() {
        List<AggregatedPrice> filteredHoubi = new ArrayList<>();
        try {
            ResponseEntity<String> houbiResponse = restTemplate.exchange(houbiUrl, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
            log.info("Houbi connected successfully");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonRoot = mapper.readTree(houbiResponse.getBody());
            JsonNode innerData = jsonRoot.get("data");
            Houbi[] houbiData = mapper.treeToValue(innerData, Houbi[].class);
            // Filter each data that matches btcusdt or ethusdt, from the filtered data, create AggregatedPrice object
            filteredHoubi = Arrays.stream(houbiData)
                .filter(data -> "btcusdt".equals(data.getSymbol().toLowerCase()) || "ethusdt".equals(data.getSymbol().toLowerCase()))
                .map(filteredData -> new AggregatedPrice(filteredData.getSymbol(), filteredData.getBid(), filteredData.getAsk(), "houbi", LocalDateTime.now()))
                .collect(Collectors.toList());

            log.info("Successfully updated latest price from Houbi");
        }
        catch (RestClientException e) {
            log.atError().withThrowable(e).log("Houbi api call failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (JsonProcessingException e) {
            log.atError().withThrowable(e).log("Houbi api call json processing failed with status {}: {}", HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return filteredHoubi;
    }
}
