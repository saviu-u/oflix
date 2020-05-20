INSERT INTO tb_funcao(nome_func)
VALUES
('Cliente'),
('Gerente'),
('Atendente'),
('Devs');

INSERT INTO tb_endereco(estado, cidade, bairro, rua, num_residencia, complemento)
VALUES
('São Paulo', 'Ribeirão Preto', 'BBB', 'RRR', 111, NULL),
('São Paulo', 'Ribeirão Preto', 'BBB', 'RRR', 111, NULL),
('São Paulo', 'Batatais', 'BBB', 'RRR', 111, NULL),
('São Paulo', 'Ribeirão Preto', 'BBB', 'RRR', 111, NULL);

INSERT INTO tb_pessoa(id_end, id_func, nome_pes, cpf, email, telefone_1, telefone_2, sexo, ativo)
VALUES
(1, 4, 'Sávio Cangussu', '111.111.111-11', 'EEE1@EEE.com', 11111111, NULL, 'M', TRUE),
(2, 4,'Raphael Bassi', '222.222.222-22', 'EEE2@EEE.com', 11111111, NULL, 'M', TRUE),
(3, 4,'Thiago Viana', '333.333.333-33', 'EEE3@EEE.com', 11111111, NULL, 'M', TRUE),
(4, 4, 'Gabriel Conti', '444.444.444-44', 'EEE4@EEE.com', 11111111, NULL, 'M', TRUE);

INSERT INTO tb_endereco(estado, cidade, bairro, rua, num_residencia, complemento)
VALUES
('São Paulo', 'Ribeirão Preto', 'Jardim Paulista', 'Rua Piracicaba', 111, NULL),
('São Paulo', 'Ribeirão Preto', 'Jardim Botânico', 'Rua das Olivas', 451, NULL);

INSERT INTO tb_pessoa(id_end, id_func, nome_pes, cpf, email, telefone_1, telefone_2, sexo, ativo)
VALUES
(5, 1, 'Adaílton Teste', '742.504.060-32', 'email1@teste.com', 39847777, NULL, 'M', TRUE),
(6, 1, 'Marilene Teste', '601.914.420-89', 'email2@teste.com', 39846666, NULL, 'F', FALSE);

INSERT INTO tb_filme(nome_filme, sinopse, quant_estoque, class_indicativa)
VALUES
('Indiana Jones e Os Salteadores da Arca Perdida', 'O arqueólogo Indiana Jones precisa encontrar a Arca da Aliança, uma relíquia bíblica que contém os dez mandamentos. Como o portador do artefato se torna invencível, os nazistas também vão fazer de tudo para adquirir esse precioso objeto.', 3, 11),
('Star Wars: Episódio IV – Uma Nova Esperança', 'A princesa Leia é mantida refém pelas forças imperiais comandadas por Darth Vader. Luke Skywalker e o capitão Han Solo precisam libertá-la e restaurar a liberdade e a justiça na galáxia.', 1, 11);

INSERT INTO tb_aluguel(id_pes, id_filme, data_aluguel, ativo)
VALUES
(5, 1, DATE '2020-05-15', TRUE),
(6, 2, DATE '2020-05-14', FALSE);

INSERT INTO tb_categoria(nome_catg)
VALUES
('Ação'),
('Comédia');

INSERT INTO tb_secao
VALUES
(1, 1),
(1, 2);

INSERT INTO tb_idioma(nome_idioma)
VALUES
('Português'),
('Inglês'),
('Espanhol');

INSERT INTO tb_disponibilidade
VALUES
(1, 1, TRUE, TRUE),
(1, 2, TRUE, TRUE),
(2, 1, TRUE, TRUE),
(2, 2, TRUE, TRUE),
(3, 1, TRUE, TRUE),
(3, 2, TRUE, FALSE);