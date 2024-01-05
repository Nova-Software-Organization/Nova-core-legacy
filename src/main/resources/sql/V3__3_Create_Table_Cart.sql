CREATE TABLE carrinho (
    idcarrinho SERIAL PRIMARY KEY,
    idcliente BIGINT,
    FOREIGN KEY (idcliente) REFERENCES cliente(idcliente)
);
