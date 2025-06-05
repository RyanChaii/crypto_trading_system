package com.personal.crypto.system.crypto_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.crypto.system.crypto_service.model.entity.TradeTransaction;

public interface TradeTransactionRepository extends JpaRepository<TradeTransaction, Long> {
}
