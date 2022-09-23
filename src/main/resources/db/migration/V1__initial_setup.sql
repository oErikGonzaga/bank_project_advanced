CREATE TABLE    bank_project_db.address (
id				INT				    NOT NULL	AUTO_INCREMENT	,
city			VARCHAR (30)	    NOT NULL					,
state			VARCHAR	(2)		    NOT NULL					,
address 		VARCHAR (30)	    NOT NULL					,
house_number	VARCHAR	(5)		    NOT NULL					,
cep				VARCHAR	(8)	        NOT NULL					,
second_address	VARCHAR	(30)							    	,
PRIMARY KEY	(id)
);

CREATE TABLE    bank_project_db.client (
id				INT					NOT NULL	AUTO_INCREMENT	,
name			VARCHAR	(150)		NOT NULL					,
birthdate		DATE				NOT NULL					,
phone			BIGINT	(15)		NOT NULL					,
email			VARCHAR (50)		NOT NULL	UNIQUE			,
document		VARCHAR	(20)		NOT NULL					,
address_id		INT 				NOT NULL					,
PRIMARY KEY	(id)												,
FOREIGN KEY (address_id) REFERENCES address (id)
);

