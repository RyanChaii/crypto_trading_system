# API Documentation

## 1. Get Latest Aggregated Prices
**URL:** `GET /api/prices/aggregatedprice`

**Description:** Returns the best bid and ask prices for BTCUSDT and ETHUSDT.  
**Response:**
```json
{
    "btcusdt": {
        "pairType": "btcusdt",
        "bestBid": 104613.19,
        "bestAsk": 104608.83
    },
    "ethusdt": {
        "pairType": "ethusdt",
        "bestBid": 2611.56,
        "bestAsk": 2611.32
    }
}
