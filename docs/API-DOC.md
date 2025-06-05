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
```

## 2. Trade Based For The Latest Best Aggregated Price
**URL:** `POST /api/trade/processorder`

**Description:** Post request to buy or sell BTC and ETH and return the transaction record.

**Request:**
```json
{
    "userId" : "test-user",
    "cryptoType" : "BTC",
    "purchaseType" : "sell",
    "quantity" : "0.015"
}
```
**Response:**
```json
{
    "message": "Successful selling of BTC",
    "pairType": "btcusdt",
    "newUsdtBalance": 44768.10350000000,
    "cryptoBalance": 0.05000000
}
```

## 3. Retrieve User's Crypto Currencies Wallet Balance
**URL:** `GET /api/wallet/retrievewallets`

**Description:** Get request that retrieve all wallet balance for that user.

**Request Header:** `key: usedId, value: test-user`

**Response:**
```json
[
    {
        "currencyType": "USDT",
        "balance": 44453.07500000
    },
    {
        "currencyType": "BTC",
        "balance": 0.05000000
    },
    {
        "currencyType": "ETH",
        "balance": 0.10000000
    }
]
```
