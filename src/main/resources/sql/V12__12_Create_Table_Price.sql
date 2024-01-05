CREATE TABLE preco_produto (
    idpreco SERIAL PRIMARY KEY,
    preco NUMERIC,
    preco_de NUMERIC,
    data_inicio_vigencia DATE,
    data_fim_vigencia DATE,
    moeda VARCHAR,
    unidade_medida VARCHAR,
    ativo INT,
    tipo_desconto VARCHAR,
    origem_preco VARCHAR,
    notas_observacoes VARCHAR,
    usuario_atualizacao VARCHAR,
    idproduto SERIAL,
    FOREIGN KEY (idproduto) REFERENCES produto(idproduto)
);
