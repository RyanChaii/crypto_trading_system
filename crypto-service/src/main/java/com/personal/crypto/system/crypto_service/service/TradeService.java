package com.personal.crypto.system.crypto_service.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.model.dto.TradeRequest;
import com.personal.crypto.system.crypto_service.model.dto.TradeResponse;
import com.personal.crypto.system.crypto_service.model.entity.UserWallet;
import com.personal.crypto.system.crypto_service.repository.UserWalletRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TradeService {
    
    private final UserWalletRepository userWalletRepository;

    private final PriceService priceService;

    public TradeService(UserWalletRepository userWalletRepository, PriceService priceService) {
        this.userWalletRepository = userWalletRepository;
        this.priceService = priceService;
    }

    public TradeResponse processTrade(TradeRequest incomingTrade) {

        // Retrieve best price from price service
        Map<String, AggregatedPriceResponse> bestPrice = priceService.getBestAggregatedPrices();

        // Retrieve user's wallet balance for usdt & incoming trade type
        UserWallet userUsdtWallet = userWalletRepository.findByUserIdAndCryptoCurrency(incomingTrade.getUserId(), "USDT")
        .orElseThrow(() -> new RuntimeException("No USDT wallet"));
        UserWallet userTradeWallet = userWalletRepository.findByUserIdAndCryptoCurrency(incomingTrade.getUserId(), incomingTrade.getCryptoType())
        .orElseThrow(() -> new RuntimeException("No " + incomingTrade.getCryptoType() + " wallet"));

        String formattedCurrencyType = incomingTrade.getCryptoType().toLowerCase() + "usdt";
        // Retrieve the best price based on desired currency
        AggregatedPriceResponse bestCurrencyPrice = bestPrice.get(formattedCurrencyType);
        BigDecimal purchaseQuantity = incomingTrade.getQuantity();

        if ("buy".equalsIgnoreCase(incomingTrade.getPurchaseType())) {

            // Retrieve best ask price
            BigDecimal bestAskPrice = bestCurrencyPrice.getBestAsk();
            BigDecimal totalAskCost = purchaseQuantity.multiply(bestAskPrice);

            // Not enough balance, cannot buy
            if (userUsdtWallet.getWalletBalance().compareTo(totalAskCost) < 0) {
                throw new IllegalArgumentException("Insufficient USDT balance");
            }

            // Detuct user's usdt wallet balance & update DB
            userUsdtWallet.setWalletBalance(userUsdtWallet.getWalletBalance().subtract(totalAskCost));
            userWalletRepository.save(userUsdtWallet);

            // Update user's wallet on the successful purchase
            userTradeWallet.setWalletBalance(userTradeWallet.getWalletBalance().add(purchaseQuantity));
            userWalletRepository.save(userTradeWallet);

        }

        if ("sell".equalsIgnoreCase(incomingTrade.getPurchaseType())) {

        }


        return null;
    }
}
