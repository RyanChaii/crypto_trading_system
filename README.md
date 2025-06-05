# Crypto Trading System

## Docs

[Requirement Understanding](./docs/requirement-understanding.txt)

[ERD Diagram](./docs/erd-diagram.png)

[API Documentation](./docs/API-DOC.md)

## Database

```
URL: http://localhost:2025/h2-console
JDBC URL: jdbc:h2:mem:cryptoservicedb
User: systemadmin
Password: (leave blank)
```
<h5>Notes</h5>

- No setup is required; the database is automatically initialized at the start of the application.

- Tables are auto-created based on JPA entities.

- Sample data is seeded via data.sql

> File location: src\main\resources\


## Unit Testing

<h5>This project includes unit tests focused on core trade functionality using JUnit 5 and Mockito.</h5>

<h4>Buy Trade</h4>

- Successful trade with sufficient USDT balance

- Failure when USDT balance is insufficient

<h4>Sell Trade</h4>

- Successful trade with sufficient crypto balance

- Failure when crypto balance is insufficient

<h4>Tech Stack</h4>

- JUnit 5

- Mockito

<h4>How to test?</h4>

right-click the TradeServiceTest.java file -> Run java

> File location: src\test\java\com\personal\crypto\system\crypto_service\


