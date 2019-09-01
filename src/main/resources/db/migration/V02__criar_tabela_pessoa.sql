CREATE TABLE PESSOA (
	COD_PESSOA BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(50) NOT NULL,
	LOGRADOURO VARCHAR(30),
	NUMERO VARCHAR(30),
	COMPLEMENTO VARCHAR(30),
	BAIRRO VARCHAR(30),
	CEP VARCHAR(30),
	CIDADE VARCHAR(30),
	ESTADO VARCHAR(30),
	ATIVO BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-12', 'Uberlândia', 'MG', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-12', 'Ribeirão Preto', 'SP', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO PESSOA (NOME, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO, ATIVO) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);
