INSERT INTO USERS (ID, NAME, EMAIL, HASHED_PASSWORD) VALUES
(1, 'Alex Smith', 'alex.smith@example.com', 'hashedPassword1'),
(2, 'Lila Grace', 'lila.grace@example.com', 'hashedPassword2');

INSERT INTO TRANSACTIONS (ID, USER_ID, TRANSACTION_DATE, AMOUNT, CURRENCY, TRANSACTION_TYPE) VALUES
(1, 1, '2024-01-15', 250.75, 'USD', 'credit'),
(2, 1, '2024-02-05', 100.00, 'USD', 'debit'),
(3, 2, '2024-01-20', 50.50, 'EUR', 'credit'),
(4, 2, '2024-02-15', 20.00, 'EUR', 'debit');