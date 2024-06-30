CREATE TABLE estoque (
    idestoque SERIAL PRIMARY KEY,
    idproduto BIGINT,
    idunidade BIGINT NOT NULL,
    quantidade_entrada INT,
    quantidade_saida INT,
    tipo_movimentacao VARCHAR(255) NOT NULL,
    data_movimentacao TIMESTAMP NOT NULL,
    FOREIGN KEY (idproduto) REFERENCES Produto(idproduto),
    FOREIGN KEY (idunidade) REFERENCES unidade(idunidade)
);
