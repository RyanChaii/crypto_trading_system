package com.personal.crypto.system.crypto_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.crypto.system.crypto_service.model.AggregatedPrice;

public interface AggregatedPriceRepository extends JpaRepository<AggregatedPrice, Long> {
    // Result might not exists, hence optional
    Optional<AggregatedPrice> findByPairTypeAndSource(String pair_type, String source);
}
