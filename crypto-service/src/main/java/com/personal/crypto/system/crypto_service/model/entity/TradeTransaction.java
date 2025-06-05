package com.personal.crypto.system.crypto_service.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trade_transaction")
@Getter
public class TradeTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @Setter
    private String userId;

    @Column(name = "trade_type")
    @Setter
    private String tradeType;

    @Column(name = "pair_type")
    @Setter
    private String pairType;

    @Column(name = "price", precision = 18, scale = 8)
    @Setter
    private BigDecimal price;

    @Column(name = "amount", precision = 18, scale = 8) // default is DECIMAL(10,2), where it will be roounded up which is undesirable, total 18 number, 10 whole number & 8 decimals
    @Setter
    private BigDecimal amount;

    @Column(name = "total_value", precision = 18, scale = 8)
    @Setter
    private BigDecimal totalValue;

    @Column(name = "date_time")
    @Setter
    private LocalDateTime dateTime;

    // Needed by JPA
    public TradeTransaction () {
    }

    public TradeTransaction (String userId, String tradeType, String pairType, BigDecimal price, BigDecimal amount, BigDecimal totalValue, LocalDateTime dateTime) {
        this.userId = userId;
        this.tradeType = tradeType;
        this.pairType = pairType;
        this.price = price;
        this.amount = amount;
        this.totalValue = totalValue;
        this.dateTime = dateTime;
    }

}
