package com.personal.crypto.system.crypto_service.service;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.repository.AggregatedPriceRepository;

@Service
public class PriceService {
    
    private final AggregatedPriceRepository aggregatedPriceRepository;

    public PriceService(AggregatedPriceRepository aggregatedPriceRepository) {
        this.aggregatedPriceRepository = aggregatedPriceRepository;
    }
}
