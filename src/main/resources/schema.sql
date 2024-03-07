
DROP TABLE t_richieste_account_transaction;

CREATE TABLE IF NOT EXISTS t_richieste_account_transaction(ID INT PRIMARY KEY, idaccount INT, fromaccountingdate VARCHAR(40), toaccountingdate VARCHAR(40), content VARCHAR(1000000));
