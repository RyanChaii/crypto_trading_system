package com.personal.crypto.system.crypto_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.crypto.system.crypto_service.model.dto.WalletResponse;
import com.personal.crypto.system.crypto_service.service.WalletService;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    
    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("/retrievewallets")
    public ResponseEntity<List<WalletResponse>> getWallets(@RequestHeader String userId) {
        walletService.getUserWallets(userId);
        return ResponseEntity.ok(walletService.getUserWallets(userId));
    }

}
