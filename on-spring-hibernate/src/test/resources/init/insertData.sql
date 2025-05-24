INSERT INTO USERS (ID, NAME, EMAIL, ENCRYPTED_PASSWORD) VALUES
(1, 'Alex Smith', 'alex.smith@example.com', '+zmKNX2kafRK6M2VZNxM+cVPCZfxQJ6r'),
(2, 'Lila Grace', 'lila.grace@example.com', 'encryptedPassword2'),
(3, 'Michael Jordan', 'michael.jordan@example.com', 'encryptedPassword3'),
(4, 'Emily Davis', 'emily.davis@example.com', 'encryptedPassword4'),
(5, 'Chris Evans', 'chris.evans@example.com', 'encryptedPassword5'),
(6, 'Sophia Turner', 'sophia.turner@example.com', 'encryptedPassword6'),
(7, 'Oliver Twist', 'oliver.twist@example.com', 'encryptedPassword7'),
(8, 'Isabella Rose', 'isabella.rose@example.com', 'encryptedPassword8'),
(9, 'Joshua Brown', 'joshua.brown@example.com', 'encryptedPassword9'),
(10, 'Chloe Kim', 'chloe.kim@example.com', 'encryptedPassword0');

INSERT INTO PAYMENTS (ID, USER_ID, PAYMENT_DATE, AMOUNT, CURRENCY, PAYMENT_TYPE) VALUES
(1, 1, '2024-01-15', 250.75, 'USD', 'credit'),
(2, 1, '2024-02-05', 100.00, 'USD', 'debit'),
(3, 2, '2024-01-20', 50.50, 'EUR', 'credit'),
(4, 2, '2024-02-15', 20.00, 'EUR', 'debit'),
(5, 3, '2024-01-25', 150.00, 'GBP', 'credit'),
(6, 3, '2024-02-18', 75.00, 'GBP', 'debit'),
(7, 4, '2024-01-30', 300.00, 'AUD', 'credit'),
(8, 4, '2024-02-20', 250.00, 'AUD', 'debit'),
(9, 5, '2024-01-10', 1000.00, 'USD', 'credit'),
(10, 5, '2024-02-25', 500.00, 'USD', 'debit'),
(11, 6, '2024-01-12', 600.50, 'CAD', 'credit'),
(12, 6, '2024-02-12', 300.00, 'CAD', 'debit'),
(13, 7, '2024-01-12', 430.75, 'JPY', 'credit'),
(14, 7, '2024-02-12', 130.25, 'JPY', 'debit'),
(15, 8, '2024-01-13', 320.00, 'USD', 'credit'),
(16, 8, '2024-02-13', 100.50, 'USD', 'debit'),
(17, 9, '2024-01-14', 90.00, 'GBP', 'credit'),
(18, 9, '2024-02-14', 45.00, 'GBP', 'debit'),
(19, 10, '2024-01-15', 200.00, 'EUR', 'credit'),
(20, 10, '2024-02-05', 120.00, 'EUR', 'debit');

INSERT INTO FINANCIAL_INSTRUMENT (ID, NAME) VALUES
(1, 'АКЦИЯ'),
(2, 'ФОНД'),
(3, 'ОБЛИГАЦИЯ'),
(4, 'КРИПТОВАЛЮТА');

INSERT INTO USER_FINANCIAL_INSTRUMENT (USER_ID, FINANCIAL_INSTRUMENT_ID) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 1),
(3, 4),
(5, 3),
(5, 4),
(7, 1),
(7, 3),
(10, 1),
(10, 2);