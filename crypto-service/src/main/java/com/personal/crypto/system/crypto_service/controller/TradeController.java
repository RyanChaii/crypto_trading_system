package com.personal.crypto.system.crypto_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.model.dto.TradeHistoryResponse;
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
        return ResponseEntity.ok(tradeService.processTrade(incomingRequest));
    }

    @GetMapping("/history")
    public ResponseEntity<List<TradeHistoryResponse>> performGetUserHistory(@RequestHeader String userId) {
        return ResponseEntity.ok(tradeService.getTradeHistory(userId));
    }
}
