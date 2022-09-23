DROP DATABASE IF EXISTS DB_BANK_SYSTEM;

CREATE DATABASE DB_BANK_SYSTEM;

USE DB_BANK_SYSTEM;

CREATE TABLE address (
id				INT				NOT NULL	AUTO_INCREMENT	,
city			VARCHAR (30)	NOT NULL					,
state			VARCHAR	(2)		NOT NULL					,
address 		VARCHAR (30)	NOT NULL					,
house_number	VARCHAR	(5)		NOT NULL					,
cep				INT		(8)		NOT NULL					,
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

ALTER TABLE transactions CHANGE COLUMN transaction_tp transaction_tp VARCHAR(30) NOT NULL;