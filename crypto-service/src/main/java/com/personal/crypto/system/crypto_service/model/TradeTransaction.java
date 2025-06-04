package com.personal.crypto.system.crypto_service.model;

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

    @Column(name = "price")
    @Setter
    private BigDecimal price;

    @Column(name = "amount")
    @Setter
    private BigDecimal amount;

    @Column(name = "total_value")
    @Setter
    private BigDecimal totalValue;

    @Column(name = "date_time")
    @Setter
    private LocalDateTime dateTime;
}
