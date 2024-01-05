CREATE TABLE item_carrinho (
    idcarrinhoItem BIGINT AUTO_INCREMENT PRIMARY KEY,
    idcarrinho BIGINT,
    idproduto BIGINT,
    quantidade INT,
    FOREIGN KEY (idcarrinho) REFERENCES carrinho(idcarrinho),
    FOREIGN KEY (idproduto) REFERENCES produto(idproduto)
);
