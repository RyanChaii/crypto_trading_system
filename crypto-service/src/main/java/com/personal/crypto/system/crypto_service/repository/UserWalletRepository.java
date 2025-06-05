package com.personal.crypto.system.crypto_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.crypto.system.crypto_service.model.entity.UserWallet;

public interface UserWalletRepository extends JpaRepository<UserWallet, Long>{
    
    List<UserWallet> findByUserId(String userId);  // get all wallet balances

    Optional<UserWallet> findByUserIdAndCryptoCurrency(String userId, String cryptoCurrency);
}