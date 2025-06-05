package com.personal.crypto.system.crypto_service.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.personal.crypto.system.crypto_service.model.dto.AggregatedPriceResponse;
import com.personal.crypto.system.crypto_service.model.entity.AggregatedPrice;
import com.personal.crypto.system.crypto_service.repository.AggregatedPriceRepository;

@Service
public class PriceService {
    
    private final AggregatedPriceRepository aggregatedPriceRepository;

    public PriceService(AggregatedPriceRepository aggregatedPriceRepository) {
        this.aggregatedPriceRepository = aggregatedPriceRepository;
    }

    public Map<String, AggregatedPriceResponse> getBestAggregatedPrices() {
        // Retrieve data from repo
        List<AggregatedPrice> prices = aggregatedPriceRepository.findAll();

        // Group by pair type and calculate best bid/ask
        Map<String, AggregatedPriceResponse> bestResult = new HashMap<>();

        // loop thru and get btcusdt first process it and then ethusdt
        for (String pairType : List.of("btcusdt", "ethusdt")) {
            // Stream thru each result that match each list item above
            List<AggregatedPrice> pairTypePrices = prices.stream()
                .filter(p -> pairType.equalsIgnoreCase(p.getPairType()))
                .toList();

            // deal with all bit then eth, 
            // return the best bid
            // the best price a buyer offers to buy at, good for sellers because they earn more, hence max
            BigDecimal bestBid = pairTypePrices.stream()
                .map(AggregatedPrice::getBidPrice)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

            // return the best ask
            // the best price a seller offers to sell at, good for buyers because they pay less, hence min
            BigDecimal bestAsk = pairTypePrices.stream()
                .map(AggregatedPrice::getAskPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);
            
            bestResult.put(pairType, new AggregatedPriceResponse(pairType, bestBid, bestAsk));
        }

        return bestResult;

    }
}
