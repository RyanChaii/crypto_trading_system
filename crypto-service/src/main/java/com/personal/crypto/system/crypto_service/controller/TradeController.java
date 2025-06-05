package com.personal.crypto.system.crypto_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.service.TradeService;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;
    
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
}
