package com.personal.crypto.system.crypto_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.model.dto.TradeRequest;
import com.personal.crypto.system.crypto_service.model.dto.TradeResponse;
import com.personal.crypto.system.crypto_service.model.entity.UserWallet;
import com.personal.crypto.system.crypto_service.repository.AggregatedPriceRepository;
import com.personal.crypto.system.crypto_service.repository.TradeTransactionRepository;
import com.personal.crypto.system.crypto_service.repository.UserWalletRepository;
import com.personal.crypto.system.crypto_service.service.PriceService;
import com.personal.crypto.system.crypto_service.service.TradeService;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
    
    @InjectMocks
    private TradeService tradeService;

    @Mock
    private UserWalletRepository userWalletRepository;

    @Mock
    private AggregatedPriceRepository aggregatedPriceRepository;

    @Mock
    private TradeTransactionRepository tradeTransactionRepository;

    @Mock
    private PriceService priceService;

    @Test
    public void testSuccessfulBuyTrade() {
        
        // Trade config
        String user = "test-user";
        String cryptoType = "ETH";
        String purchaseType = "buy";
        BigDecimal purchaseQuantity = new BigDecimal("0.1");
        // Trade request
        TradeRequest incomingTradeRequest = new TradeRequest(user, cryptoType, purchaseType, purchaseQuantity);

        // Mock the aggregated best price map
        Map<String, AggregatedPriceResponse> mockedMap = new HashMap<>();
        mockedMap.put("ethusdt", new AggregatedPriceResponse("ethusdt", new BigDecimal("2600"), new BigDecimal("2550")));
        when(priceService.getBestAggregatedPrices()).thenReturn(mockedMap);

        // Mock the wallet
        UserWallet usdtWallet = new UserWallet("test-user", "USDT", new BigDecimal("50000"));
        UserWallet ethWallet = new UserWallet("test-user", "ETH", new BigDecimal("0"));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "USDT")).thenReturn(Optional.of(usdtWallet));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "ETH")).thenReturn(Optional.of(ethWallet));

        // Mock user exists
        when(userWalletRepository.existsByUserId(user)).thenReturn(true);

        // Mock the save
        when(userWalletRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(tradeTransactionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock the trade
        TradeResponse tradeResponse = this.tradeService.processTrade(incomingTradeRequest);
        assertEquals("Successful purchase of ETH", tradeResponse.getMessage());
        assertEquals(new BigDecimal("49745.0"), tradeResponse.getNewUsdtBalance());
        assertEquals(new BigDecimal("0.1"), tradeResponse.getCryptoBalance());

    }

    @Test
    public void testSuccessfulSellTrade() {
        
        // Trade config
        String user = "test-user";
        String cryptoType = "BTC";
        String purchaseType = "sell";
        BigDecimal purchaseQuantity = new BigDecimal("0.1");
        // Trade request
        TradeRequest incomingTradeRequest = new TradeRequest(user, cryptoType, purchaseType, purchaseQuantity);

        // Mock the aggregated best price map
        Map<String, AggregatedPriceResponse> mockedMap = new HashMap<>();
        mockedMap.put("btcusdt", new AggregatedPriceResponse("btcusdt", new BigDecimal("104500"), new BigDecimal("104000")));
        when(priceService.getBestAggregatedPrices()).thenReturn(mockedMap);

        // Mock the wallet
        UserWallet usdtWallet = new UserWallet("test-user", "USDT", new BigDecimal("50000"));
        UserWallet btcWallet = new UserWallet("test-user", "BTC", new BigDecimal("1"));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "USDT")).thenReturn(Optional.of(usdtWallet));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "BTC")).thenReturn(Optional.of(btcWallet));

        // Mock user exists
        when(userWalletRepository.existsByUserId(user)).thenReturn(true);

        // Mock the save
        when(userWalletRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        when(tradeTransactionRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock the trade
        TradeResponse tradeResponse = this.tradeService.processTrade(incomingTradeRequest);
        assertEquals("Successful selling of BTC", tradeResponse.getMessage());
        assertEquals(new BigDecimal("60450.0"), tradeResponse.getNewUsdtBalance());
        assertEquals(new BigDecimal("0.9"), tradeResponse.getCryptoBalance());

    }

    @Test
    public void testFailedBuyTrade() {
        
        // Trade config
        String user = "test-user";
        String cryptoType = "BTC";
        String purchaseType = "buy";
        BigDecimal purchaseQuantity = new BigDecimal("1");
        // Trade request
        TradeRequest incomingTradeRequest = new TradeRequest(user, cryptoType, purchaseType, purchaseQuantity);

        // Mock the aggregated best price map
        Map<String, AggregatedPriceResponse> mockedMap = new HashMap<>();
        mockedMap.put("btcusdt", new AggregatedPriceResponse("btcusdt", new BigDecimal("104500"), new BigDecimal("104000")));
        when(priceService.getBestAggregatedPrices()).thenReturn(mockedMap);

        // Mock the wallet
        UserWallet usdtWallet = new UserWallet("test-user", "USDT", new BigDecimal("50000"));
        UserWallet btcWallet = new UserWallet("test-user", "BTC", new BigDecimal("0"));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "USDT")).thenReturn(Optional.of(usdtWallet));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "BTC")).thenReturn(Optional.of(btcWallet));

        // Mock user exists
        when(userWalletRepository.existsByUserId(user)).thenReturn(true);

        // Mock expected exception when processing trade
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        tradeService.processTrade(incomingTradeRequest);
        });

        assertTrue(exception.getMessage().toLowerCase().contains("usdt balance"), "Expected error message to mention 'usdt balance'");

    }

    @Test
    public void testFailedSellTrade() {
        
        // Trade config
        String user = "test-user";
        String cryptoType = "ETH";
        String purchaseType = "sell";
        BigDecimal purchaseQuantity = new BigDecimal("1");
        // Trade request
        TradeRequest incomingTradeRequest = new TradeRequest(user, cryptoType, purchaseType, purchaseQuantity);

        // Mock the aggregated best price map
        Map<String, AggregatedPriceResponse> mockedMap = new HashMap<>();
        mockedMap.put("ethusdt", new AggregatedPriceResponse("ethusdt", new BigDecimal("2600"), new BigDecimal("2550")));
        when(priceService.getBestAggregatedPrices()).thenReturn(mockedMap);

        // Mock the wallet
        UserWallet usdtWallet = new UserWallet("test-user", "USDT", new BigDecimal("50000"));
        UserWallet ethWallet = new UserWallet("test-user", "ETH", new BigDecimal("0"));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "USDT")).thenReturn(Optional.of(usdtWallet));
        when(userWalletRepository.findByUserIdAndCryptoCurrency("test-user", "ETH")).thenReturn(Optional.of(ethWallet));

        // Mock user exists
        when(userWalletRepository.existsByUserId(user)).thenReturn(true);

        // Mock expected exception when processing trade
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        tradeService.processTrade(incomingTradeRequest);
        });

        assertTrue(exception.getMessage().toLowerCase().contains("crypto balance"), "Expected error message to mention 'crypto balance'");
    }

}

