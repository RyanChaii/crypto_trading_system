package com.personal.crypto.system.crypto_service.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.model.dto.TradeRequest;
import com.personal.crypto.system.crypto_service.model.dto.TradeResponse;
import com.personal.crypto.system.crypto_service.repository.AggregatedPriceRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TradeService {
    
    private final AggregatedPriceRepository aggregatedPriceRepository;

    private final PriceService priceService;

    public TradeService(AggregatedPriceRepository aggregatedPriceRepository, PriceService priceService) {
        this.aggregatedPriceRepository = aggregatedPriceRepository;
        this.priceService = priceService;
    }

    public TradeResponse processTrade(TradeRequest incomingTrade) {

        // Retrieve best price from price service
        Map<String, AggregatedPriceResponse> bestPrice = priceService.getBestAggregatedPrices();

        return null;
    }
}
