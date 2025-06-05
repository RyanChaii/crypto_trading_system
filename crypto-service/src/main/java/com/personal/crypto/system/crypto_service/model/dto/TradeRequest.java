package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeRequest {

    private String userId;
    private String cryptoType;
    private String purchaseType; // buy or sell
    private BigDecimal quantity;

    public TradeRequest(String userId, String cryptoType, String purchaseType, BigDecimal quantity) {
        this.userId = userId;
        this.cryptoType = cryptoType;
        this.purchaseType = purchaseType;
        this.quantity = quantity;
    }
}
