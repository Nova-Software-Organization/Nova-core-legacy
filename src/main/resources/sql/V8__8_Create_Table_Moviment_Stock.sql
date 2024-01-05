CREATE TABLE estoque_movimento (
    id SERIAL PRIMARY KEY,
    idestoque BIGINT NOT NULL,
    quantidade_saida INT,
    data_movimentacao TIMESTAMP NOT NULL,
    FOREIGN KEY (idestoque) REFERENCES estoque(idestoque)
);
