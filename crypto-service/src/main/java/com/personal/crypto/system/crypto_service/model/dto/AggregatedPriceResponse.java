package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggregatedPriceResponse {
    private String pairType;
    private BigDecimal bestBid;
    private BigDecimal bestAsk;

    public AggregatedPriceResponse(String pairType, BigDecimal bestBid, BigDecimal bestAsk) {
        this.pairType = pairType;
        this.bestBid = bestBid;
        this.bestAsk = bestAsk;
    }
}
