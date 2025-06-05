package com.personal.crypto.system.crypto_service.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponse {

    private String currencyType;
    private BigDecimal balance;

    public WalletResponse(String currencyType, BigDecimal balance) {
        this.currencyType = currencyType;
        this.balance = balance;
    }
}
