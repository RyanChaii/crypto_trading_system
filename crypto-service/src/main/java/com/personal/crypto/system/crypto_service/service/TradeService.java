package com.personal.crypto.system.crypto_service.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.model.dto.TradeRequest;
import com.personal.crypto.system.crypto_service.model.dto.TradeResponse;
import com.personal.crypto.system.crypto_service.model.entity.TradeTransaction;
import com.personal.crypto.system.crypto_service.model.entity.UserWallet;
import com.personal.crypto.system.crypto_service.repository.TradeTransactionRepository;
import com.personal.crypto.system.crypto_service.repository.UserWalletRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TradeService {
    
    private final UserWalletRepository userWalletRepository;

    private final TradeTransactionRepository tradeTransactionRepository;

    private final PriceService priceService;

    public TradeService(UserWalletRepository userWalletRepository, TradeTransactionRepository tradeTransactionRepository, PriceService priceService) {
        this.userWalletRepository = userWalletRepository;
        this.tradeTransactionRepository = tradeTransactionRepository;
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
        BigDecimal quantity = incomingTrade.getQuantity();
        String userId = incomingTrade.getUserId();

        if ("buy".equalsIgnoreCase(incomingTrade.getPurchaseType())) {

            // Retrieve best ask price
            BigDecimal bestAskPrice = bestCurrencyPrice.getBestAsk();
            BigDecimal totalAskCost = quantity.multiply(bestAskPrice);

            // Not enough balance, cannot buy
            if (userUsdtWallet.getWalletBalance().compareTo(totalAskCost) < 0) {
                throw new IllegalArgumentException("Insufficient USDT balance");
            }

            // Detuct user's usdt wallet balance & update DB
            userUsdtWallet.setWalletBalance(userUsdtWallet.getWalletBalance().subtract(totalAskCost));
            userWalletRepository.save(userUsdtWallet);

            // Update user's wallet on the successful purchase
            userTradeWallet.setWalletBalance(userTradeWallet.getWalletBalance().add(quantity));
            userWalletRepository.save(userTradeWallet);

            // Create trade record
            TradeTransaction newRecord = new 
            TradeTransaction(userId, incomingTrade.getPurchaseType(), formattedCurrencyType, bestAskPrice, quantity, totalAskCost, LocalDateTime.now());
            // Save the record
            tradeTransactionRepository.save(newRecord);
        }

        if ("sell".equalsIgnoreCase(incomingTrade.getPurchaseType())) {

             // Retrieve best bid price
            BigDecimal bestBidPrice = bestCurrencyPrice.getBestBid();
            BigDecimal totalBidCost = quantity.multiply(bestBidPrice);

            // Not enough crypto, cannot sell
            if (userTradeWallet.getWalletBalance().compareTo(quantity) < 0) {
                throw new IllegalArgumentException("Insufficient crypto balance");
            }

            // Add user's usdt wallet balance & update DB
            userUsdtWallet.setWalletBalance(userUsdtWallet.getWalletBalance().add(totalBidCost));
            userWalletRepository.save(userUsdtWallet);

            // Update user's wallet on the successful sold
            userTradeWallet.setWalletBalance(userTradeWallet.getWalletBalance().subtract(quantity));
            userWalletRepository.save(userTradeWallet);

            // Create trade record
            TradeTransaction newRecord = new 
            TradeTransaction(userId, incomingTrade.getPurchaseType(), formattedCurrencyType, bestBidPrice, quantity, totalBidCost, LocalDateTime.now());
            // Save the record
            tradeTransactionRepository.save(newRecord);
        }




        return null;
    }
}
