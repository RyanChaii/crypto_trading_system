package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeResponse {
    
    private String message;
    private String cryptoType;
    private BigDecimal newUsdtBalance;
    private BigDecimal cryptoBalance;

    public TradeResponse(String message, String cryptoType, BigDecimal newUsdtBalance, BigDecimal cryptoBalance) {
        this.message = message;
        this.cryptoType = cryptoType;
        this.newUsdtBalance = newUsdtBalance;
        this.cryptoBalance = cryptoBalance;
    }
}
