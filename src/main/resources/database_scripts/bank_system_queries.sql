
-- Autenticar
SELECT	c.name 				,
		ba.balance 			,
		ba.account_tp 		,
		ba.deactivation_dt 	,
		a.password 			,
		a.client_block
FROM    bank_account ba	INNER JOIN client c ON (c.id = ba.client_id)
							INNER JOIN access a ON (a.account_id = ba.id)
WHERE   ba.account_number = :conta AND
        ba.agency           = :agencia;

-- AtualizarSaldo (faz update do saldo na tabela bank_account pelo numero da conta e numero da agencia)
UPDATE bank_account ba
    SET ba.balance = :saldo
WHERE ba.account_number = :conta    AND
      ba.agency         = :agencia;

-- InativarConta
UPDATE bank_account ba
    SET ba.deactivation_dt = DATETIME()
WHERE ba.account_number = :conta    AND
      ba.agency         = :agencia;

-- AtivarConta
UPDATE bank_account ba
    SET ba.deactivation_dt = NULL
WHERE   ba.account_number = :conta AND
        ba.agency = :agencia;

-- HistóricoTransações
INSERT INTO transactions (value, operation, operation_dt, account_id, transfer_account_id, transaction_tp)
VALUES (,,,,,);

-- ListarTransacoes
SELECT * FROM transactions

-- CadastroConta
INSERT INTO bank_account (agency, balance, account_tp)
VALUES ();

-- CadastroEndereco
INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ();

-- CadastroCliente
INSERT INTO client ()
VALUES (name, birthdate, phone, email, person_tp, document_tp, document);

-- CadastroAcesso
INSERT INTO access (password)
VALUES ();