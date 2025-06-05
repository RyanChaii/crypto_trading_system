package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeHistoryResponse {
    
    private String userId;
    private String tradeType;
    private String pairType;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal totalPrice;
    private LocalDateTime transactionDateTime;

    public TradeHistoryResponse(String userId, String tradeType, String pairType, BigDecimal price, BigDecimal amount, BigDecimal totalPrice, LocalDateTime transactionDateTime) {
        this.userId = userId;
        this.tradeType = tradeType;
        this.pairType = pairType;
        this.price = price;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.transactionDateTime = transactionDateTime;
    }
}
