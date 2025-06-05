package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeResponse {
    
    private String message;
    private String pairType;
    private BigDecimal newUsdtBalance;
    private BigDecimal cryptoBalance;

    public TradeResponse(String message, String pairType, BigDecimal newUsdtBalance, BigDecimal cryptoBalance) {
        this.message = message;
        this.pairType = pairType;
        this.newUsdtBalance = newUsdtBalance;
        this.cryptoBalance = cryptoBalance;
    }
}
