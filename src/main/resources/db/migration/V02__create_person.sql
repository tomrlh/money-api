CREATE TABLE person (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	active BOOLEAN NOT NULL,
	street VARCHAR(50),
	number INTEGER,
	complement VARCHAR(100),
	district VARCHAR(50),
	postal_code INTEGER,
	city VARCHAR(50),
	state VARCHAR(30)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person values (1, 'User', true, 'address 1', 1231, 'complement 1', 'district 1', 12345678, 'city 1', 'state 1');
INSERT INTO person values (2, 'User', true, 'address 2', 1232, 'complement 2', 'district 2', 12345679, 'city 2', 'state 2');
INSERT INTO person values (3, 'User', true, 'address 3', 1233, 'complement 3', 'district 3', 123456710, 'city 3', 'state 3');
INSERT INTO person values (4, 'User', true, 'address 4', 1234, 'complement 4', 'district 4', 123456711, 'city 4', 'state 4');
INSERT INTO person values (5, 'User', true, 'address 5', 1235, 'complement 5', 'district 5', 123456712, 'city 5', 'state 5');
