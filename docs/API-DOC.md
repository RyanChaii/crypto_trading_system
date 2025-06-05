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

## 4. Retrieve User's Trading History
**URL:** `GET /api/trade/history`

**Description:** Get request that retrieve all trade transaction made by the user.

**Request Header:** `key: usedId, value: test-user`

**Response:**
```json
[
    {
        "userId": "test-user",
        "tradeType": "sell",
        "pairType": "btcusdt",
        "price": 104127.00000000,
        "amount": 0.01000000,
        "totalPrice": 1041.27000000,
        "transactionDateTime": "2025-06-05T22:17:03.073929"
    },
    {
        "userId": "test-user",
        "tradeType": "buy",
        "pairType": "btcusdt",
        "price": 104112.11000000,
        "amount": 0.05000000,
        "totalPrice": 5205.60550000,
        "transactionDateTime": "2025-06-05T22:16:56.860793"
    },
    {
        "userId": "test-user",
        "tradeType": "buy",
        "pairType": "ethusdt",
        "price": 2586.92000000,
        "amount": 1.50000000,
        "totalPrice": 3880.38000000,
        "transactionDateTime": "2025-06-05T22:16:42.489303"
    },
    {
        "userId": "test-user",
        "tradeType": "buy",
        "pairType": "ethusdt",
        "price": 2586.31000000,
        "amount": 0.50000000,
        "totalPrice": 1293.15500000,
        "transactionDateTime": "2025-06-05T22:16:37.122493"
    }
]
```
