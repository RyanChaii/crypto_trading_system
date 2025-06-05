package com.personal.crypto.system.crypto_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.service.PriceService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
    this.priceService = priceService;
    }

    @GetMapping("/aggregatedprice")
    public ResponseEntity<Map<String, AggregatedPriceResponse>> getAggregatedPrices() {
        return ResponseEntity.ok(priceService.getBestAggregatedPrices());
    }

}
