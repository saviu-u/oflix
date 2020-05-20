CREATE TABLE tb_funcao(
	id SERIAL,
	nome_func VARCHAR(12) NOT NULL,
	CONSTRAINT tb_funcao_id PRIMARY KEY(id)
);

CREATE TABLE tb_endereco(
	id SERIAL,
	estado VARCHAR(45) NOT NULL,
	cidade VARCHAR(45) NOT NULL,
	bairro VARCHAR(45) NOT NULL,
	rua VARCHAR(45) NOT NULL,
	num_residencia INTEGER NOT NULL,
	complemento VARCHAR(10),
	CONSTRAINT tb_endereco_id PRIMARY KEY(id)
);

CREATE TABLE tb_pessoa(
	id SERIAL,
	id_end INTEGER UNIQUE,
	id_func INTEGER,
	nome_pes VARCHAR(255) NOT NULL,
	cpf VARCHAR(14) NOT NULL UNIQUE,
	email VARCHAR(60) NOT NULL UNIQUE,
	telefone_1 INTEGER NOT NULL,
	telefone_2 INTEGER,
	sexo VARCHAR(1) NOT NULL,
	ativo BOOL NOT NULL,
	CONSTRAINT tb_pessoa_id PRIMARY KEY(id),
	CONSTRAINT tb_pessoa_funcao_id FOREIGN KEY(id_func) REFERENCES tb_funcao(id),
	CONSTRAINT tb_pessoa_endereco_id FOREIGN KEY(id_end) REFERENCES tb_endereco(id)
);

CREATE TABLE tb_categoria(
	id SERIAL,
	nome_catg VARCHAR(20) NOT NULL UNIQUE,
	CONSTRAINT tb_categoria_id PRIMARY KEY(id)
);

CREATE TABLE tb_filme(
	id SERIAL,
	id_catg INTEGER,
	nome_filme VARCHAR(255) NOT NULL,
	sinopse VARCHAR(511),
	quant_estoque INTEGER NOT NULL,
	class_indicativa INTEGER NOT NULL,
	CONSTRAINT tb_filme_id PRIMARY KEY(id),
	CONSTRAINT tb_filme_categ_id FOREIGN KEY(id_catg) REFERENCES tb_categoria(id)
);

CREATE TABLE tb_aluguel(
	id SERIAL,
	id_pes INTEGER,
	id_filme INTEGER,
	data_aluguel DATE NOT NULL,
	ativo BOOL NOT NULL,
	CONSTRAINT tb_aluguel_id PRIMARY KEY(id),
	CONSTRAINT tb_aluguel_pessoa_id FOREIGN KEY(id_pes) REFERENCES tb_pessoa(id),
	CONSTRAINT tb_aluguel_filme_id FOREIGN KEY(id_filme) REFERENCES tb_filme(id)
);