package com.personal.crypto.system.crypto_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.service.PriceService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {
    
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
    this.priceService = priceService;
    }

}
