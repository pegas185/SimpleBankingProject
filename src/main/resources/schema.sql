CREATE TABLE user
(
    id       INTEGER PRIMARY KEY,
    username VARCHAR(64) NOT NULL
);

CREATE TABLE customer
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR(64) NOT NULL,
    surname VARCHAR(64) NOT NULL
);

CREATE TABLE account
(
    id IDENTITY NOT NULL PRIMARY KEY,
    customer_id INTEGER NOT NULL
);

CREATE TABLE transaction
(
    id IDENTITY NOT NULL PRIMARY KEY,
    account_id INTEGER NOT NULL,
    transaction_type SMALLINT NOT NULL,
    amount DECIMAL NOT NULL

);