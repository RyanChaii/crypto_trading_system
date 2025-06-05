package com.personal.crypto.system.crypto_service.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.model.dto.WalletResponse;
import com.personal.crypto.system.crypto_service.model.entity.UserWallet;
import com.personal.crypto.system.crypto_service.repository.UserWalletRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class WalletService {

    private final UserWalletRepository userWalletRepository;

    public WalletService(UserWalletRepository userWalletRepository) {
        this.userWalletRepository = userWalletRepository;
    }

    public List<WalletResponse> getUserWallets(String userId) {

        userIdValidation(userId);

        List<UserWallet> allWallets = userWalletRepository.findByUserId(userId);

        if (allWallets.isEmpty()) {
            throw new IllegalArgumentException("No wallet found for user: " + userId);
        }

        // Simple wallet validation
        Set<String> supportedCurrencies = Set.of("BTC", "ETH");

        for (UserWallet wallet : allWallets) {
            String currency = wallet.getCryptoCurrency().toUpperCase();
            if (!supportedCurrencies.contains(currency)) {
                throw new IllegalArgumentException("Unsupported currency in wallet: " + currency);
            }
        }

        // Stream wallet list and create wallet response object from it
        List<WalletResponse> retrievedResults = allWallets.stream()
            .map(w -> new WalletResponse(w.getCryptoCurrency(), w.getWalletBalance()))
            .collect(Collectors.toList());

        return retrievedResults;
    }

    public void userIdValidation (String userId) {
        // Validate userId
        if (userId.isBlank()) {
            throw new IllegalArgumentException("User ID is required");
        }

        if (!userWalletRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException("User not found");
        }

    }
}
