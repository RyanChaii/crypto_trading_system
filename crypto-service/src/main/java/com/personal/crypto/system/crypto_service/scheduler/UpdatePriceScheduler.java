package com.personal.crypto.system.crypto_service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdatePriceScheduler {
    
    @Scheduled(fixedRate = 10000) // 1000 = 1 sec
    public void fetchAndStoreBestPrice() {
        // call source API
        // compare bid and ask prices
        // store the best one into AggregatedPrice table
    }
}
