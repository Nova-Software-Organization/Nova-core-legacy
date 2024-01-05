CREATE TABLE produto (
    idproduto SERIAL PRIMARY KEY,
    idmid SERIAL,
    idcategoria SERIAL NOT NULL,
    nome VARCHAR,
    idfornecedor SERIAL,
    idpreco SERIAL,
    idestoque SERIAL,
    idunidade SERIAL,
    descricao VARCHAR,
    ativo INT,
    sku VARCHAR,
    FOREIGN KEY (idmid) REFERENCES midia(idmid),
    FOREIGN KEY (idcategoria) REFERENCES produto_categoria(idcategoria),
    FOREIGN KEY (idfornecedor) REFERENCES fornecedor(idfornecedor),
    FOREIGN KEY (idpreco) REFERENCES preco(idpreco),
    FOREIGN KEY (idestoque) REFERENCES estoque(idestoque),
    FOREIGN KEY (idunidade) REFERENCES unidade(idunidade)
);
