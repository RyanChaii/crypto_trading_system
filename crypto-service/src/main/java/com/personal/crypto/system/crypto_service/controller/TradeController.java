package com.personal.crypto.system.crypto_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.model.dto.TradeRequest;
import com.personal.crypto.system.crypto_service.model.dto.TradeResponse;
import com.personal.crypto.system.crypto_service.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;
    
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/processorder")
    public ResponseEntity<TradeResponse> performTrade(@RequestBody TradeRequest incomingRequest) {
        tradeService.processTrade(incomingRequest);
        return null;
        // return ResponseEntity.ok(tradeService)
    }
}
