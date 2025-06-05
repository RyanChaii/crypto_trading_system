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
    private String pairType;
    private String purchaseType; // buy or sell
    private BigDecimal quantity;

    public TradeRequest(String userId, String pairType, String purchaseType, BigDecimal quantity) {
        this.userId = userId;
        this.pairType = pairType;
        this.purchaseType = purchaseType;
        this.quantity = quantity;
    }
}
