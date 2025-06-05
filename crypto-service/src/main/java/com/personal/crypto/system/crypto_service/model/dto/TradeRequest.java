package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeRequest {

    private Long userId;
    private String pairType;
    private String purchaseType;
    private BigDecimal quantity;

    public TradeRequest(Long userId, String pairType, String purchaseType, BigDecimal quantity) {
        this.userId = userId;
        this.pairType = pairType;
        this.purchaseType = purchaseType;
        this.quantity = quantity;
    }
}
