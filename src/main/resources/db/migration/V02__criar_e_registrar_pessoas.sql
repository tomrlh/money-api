CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(50),
	numero NUMBER,
	complemento VARCHAR(100),
	bairro VARCHAR(50),
	cep NUMBER,
	cidade VARCHAR(50),
	estado VARCHAR(30)	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa values (1, 'User', true, 'endereco 1', 1231, 'complemento 1', 'bairro 1', 12345678, 'cidade 1', 'estado 1');
INSERT INTO pessoa values (2, 'User', true, 'endereco 2', 1232, 'complemento 2', 'bairro 2', 12345679, 'cidade 2', 'estado 2');
INSERT INTO pessoa values (3, 'User', true, 'endereco 3', 1233, 'complemento 3', 'bairro 3', 123456710, 'cidade 3', 'estado 3');
INSERT INTO pessoa values (4, 'User', true, 'endereco 4', 1234, 'complemento 4', 'bairro 4', 123456711, 'cidade 4', 'estado 4');
INSERT INTO pessoa values (5, 'User', true, 'endereco 5', 1235, 'complemento 5', 'bairro 5', 123456712, 'cidade 5', 'estado 5');