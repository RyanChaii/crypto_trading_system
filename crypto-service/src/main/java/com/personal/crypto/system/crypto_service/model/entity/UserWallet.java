package com.personal.crypto.system.crypto_service.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "user_wallet")
@Getter
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @Setter
    private String userId;

    @Column(name = "crypto_currency")
    @Setter
    private String cryptoCurrency;

    @Column(name = "wallet_balance", precision = 18, scale = 8)
    @Setter
    private BigDecimal walletBalance;

    public UserWallet(String userId, String cryptoCurrency, BigDecimal walletBalance) {
        this.userId = userId;
        this.cryptoCurrency = cryptoCurrency;
        this.walletBalance = walletBalance;
    }
}
