package com.personal.crypto.system.crypto_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    
    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }
}
