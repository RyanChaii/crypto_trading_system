package com.personal.crypto.system.crypto_service.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aggregated_price", uniqueConstraints = @UniqueConstraint(columnNames = {"pair_type", "source"})) // prevent accidental duplicate
@Getter
public class AggregatedPrice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pair_type")
    @Setter
    private String pairType;

    @Column(name = "bid_price")
    @Setter
    private BigDecimal bidPrice;

    @Column(name = "ask_price")
    @Setter
    private BigDecimal askPrice;

    @Column(name = "source")
    @Setter
    private String source;

    @Column(name = "date_time")
    @Setter
    private LocalDateTime dateTime;

    // Needed by JPA
    public AggregatedPrice() {
    }

    public AggregatedPrice(String pairType, BigDecimal bidPrice, BigDecimal askPrice, String source, LocalDateTime dateTime){
        this.pairType = pairType;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.source = source;
        this.dateTime = dateTime;
    }
}
