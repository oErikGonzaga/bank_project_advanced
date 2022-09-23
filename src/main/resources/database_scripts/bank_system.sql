DROP DATABASE IF EXISTS DB_BANK_SYSTEM;

CREATE DATABASE DB_BANK_SYSTEM;

USE DB_BANK_SYSTEM;

CREATE TABLE address (
id				INT				NOT NULL	AUTO_INCREMENT	,
city			VARCHAR (30)	NOT NULL					,
state			VARCHAR	(2)		NOT NULL					,
address 		VARCHAR (30)	NOT NULL					,
house_number	VARCHAR	(5)		NOT NULL					,
cep				VARCHAR	(8)	    NOT NULL					,
address_2		VARCHAR	(30)								,
PRIMARY KEY	(id)
);

CREATE TABLE client (
id				INT					NOT NULL	AUTO_INCREMENT	,
name			VARCHAR	(150)		NOT NULL					,
birthdate		DATE				NOT NULL					,
phone			BIGINT	(15)		NOT NULL					,
email			VARCHAR (50)		NOT NULL	UNIQUE			,
person_tp		VARCHAR (2)						DEFAULT 'PF'	,
document_tp		VARCHAR (10)					DEFAULT	'CPF'	,
document		VARCHAR	(20)		NOT NULL					,
address_id		INT 				NOT NULL					,
PRIMARY KEY	(id)												,
FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE TABLE bank_account (
id					INT				NOT NULL	AUTO_INCREMENT		,
account_number		INT 	(10)	NOT NULL						,
client_id			INT 			NOT NULL						,
agency				INT 	(4)					DEFAULT 1			,
balance				DECIMAL (13, 2)				DEFAULT 0			,
registration_dt		DATETIME		NOT NULL						,
account_tp			VARCHAR (30)	NOT NULL	DEFAULT 'CORRENTE'	,
deactivation_dt		DATETIME										,
PRIMARY KEY	(id)													,
FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE transactions (
id						INT				NOT NULL	AUTO_INCREMENT	,
value					DECIMAL	(13, 2)	NOT NULL					,
operation				VARCHAR	(30)	NOT NULL					,
operation_dt			DATETIME		NOT NULL					,
account_id				INT		(10)	NOT NULL					,
transfer_account_id	    INT		(10)						        ,
transaction_tp			VARCHAR (10)	NOT NULL					,
PRIMARY KEY	(id)													,
FOREIGN KEY (account_id) REFERENCES bank_account (id)
);

CREATE TABLE access (
client_id			INT 			NOT NULL 			,
account_id			INT				NOT NULL			,
password			VARCHAR (10)	NOT NULL			,
PRIMARY KEY	(client_id, account_id)						,
FOREIGN KEY (client_id) REFERENCES client (id)			,
FOREIGN KEY (account_id) REFERENCES bank_account (id)
);

ALTER TABLE access ADD COLUMN client_block DATETIME AFTER password;
ALTER TABLE client CHANGE COLUMN person_tp person_tp ENUM('PF', 'PJ') NOT NULL  DEFAULT 'PF';
ALTER TABLE client CHANGE COLUMN document_tp document_tp ENUM('CPF', 'CNPJ') NOT NULL  DEFAULT 'CPF';
ALTER TABLE transactions CHANGE COLUMN operation operation ENUM('ENTRADA', 'SAIDA', 'ACESSO') NOT NULL;
ALTER TABLE client CHANGE COLUMN phone phone VARCHAR(11) NOT NULL;
ALTER TABLE transactions CHANGE COLUMN transaction_tp transaction_tp VARCHAR(30) NOT NULL;


-- Inserts Tabela address
INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Avenida Imirim', 2383, 02465300, 'Loja');

INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', '641 A', 02542110, 'Casa 2');

INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Mere Marie', '472', 09929129, 'Apto 8');

INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Algum Lugar', '100', 05939149, 'Fundos');

INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Outro Lugar', '200', 01234567, 'Blc 2 - Apto 27');


-- Inserts Tabela client
INSERT INTO client (name, birthdate, phone, email, person_tp, document_tp, document, address_id)
VALUES ('Erik Gonzaga MEI', '2000-03-05', 1122563138, 'erikgonzagamei@email.com', 'PJ', 'CNPJ', 35821066000127, 1);

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Abner Amós', '1993-08-10', 11973851774, 'abner.fsouza@hotmail.com', 42104482810, 2);

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Ronan Roldao', '1987-05-05', 11912345678, 'ronan.roldao@gmail.com', 19876543210, 3);

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Nadine Roldao', '2001-12-25', 11987651234, 'nadine.roldao@yahoo.com.br', 13579135791, 4);

INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Ricardo Silva', '2001-07-02', 11981742639, 'ricardoslv@ig.br', 97531975323, 5);


-- Inserts Tabela bank_account
INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (201, 1, 20000, '2019-02-01');

INSERT INTO bank_account (account_number, client_id, registration_dt, balance)
VALUES (202, 2, '2022-08-03', 545.38);

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (203, 3, 1745.88, '2022-08-03', 'INVESTIMENTO');

INSERT INTO bank_account (account_number, client_id, balance, registration_dt, account_tp)
VALUES (204, 4, 100, '2022-08-10', 'POUPANCA' );

INSERT INTO bank_account (account_number, client_id, balance, registration_dt)
VALUES (205, 5, 3500, '2015-05-15');


-- ACCESS
-- Inserts Tabela access
INSERT INTO access (client_id, account_id, password)
VALUES (4, 1, '123456');

INSERT INTO access (client_id, account_id, password)
VALUES (1, 3, 'abc123');

INSERT INTO access (client_id, account_id, password)
VALUES (2, 4, '123456');

INSERT INTO access (client_id, account_id, password)
VALUES (3, 5, 'abc123');

INSERT INTO access (client_id, account_id, password)
VALUES (5, 6, '1qaz@wsx');


-- TRANSACTION
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (100.00, 'ENTRADA', '2022-06-19 17:30:00', 1, 'DEPOSITO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (100.00, 'SAIDA', '2022-08-08 17:30:00', 1, 'SAQUE');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (250.45, 'SAIDA', '2022-08-01 17:30:00', 1, 'TRANSFERENCIA', 2);

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-07-06 17:30:00', 1, 'TENTATIVA_ACESSO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (110.00, 'ENTRADA', '2022-08-12 17:30:00', 6, 'DEPOSITO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (50.00, 'SAIDA', '2022-06-22 17:30:00', 6, 'SAQUE');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (1000, 'SAIDA', '2022-07-13 17:30:00', 6, 'TRANSFERENCIA', 3);

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-05-31 17:30:00', 6, 'TENTATIVA_ACESSO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (80.00, 'ENTRADA', '2022-07-18 17:30:00', 3, 'DEPOSITO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (60.00, 'SAIDA', '2022-06-25 17:30:00', 3, 'SAQUE');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (30.00, 'SAIDA', '2022-08-11 17:30:00', 3, 'TRANSFERENCIA', 4);

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-06-30 17:30:00', 3, 'TENTATIVA_ACESSO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (300.00, 'ENTRADA', '2022-07-20 17:30:00', 4, 'DEPOSITO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (70.00, 'SAIDA', '2022-07-15 17:30:00', 4, 'SAQUE');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (750.00, 'SAIDA', '2022-08-06 17:30:00', 4, 'TRANSFERENCIA', 5);

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-05 17:30:00', 4, 'TENTATIVA_ACESSO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (500.00, 'ENTRADA', '2022-08-04 17:30:00', 5, 'DEPOSITO');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (90.00, 'SAIDA', '2022-08-03 17:30:00', 5, 'SAQUE');

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp, transfer_account_id)
VALUES (20.00, 'SAIDA', '2022-08-02 17:30:00', 5, 'TRANSFERENCIA', 1);

INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (0, 'ACESSO', '2022-08-01 17:30:00', 5, 'TENTATIVA_ACESSO');