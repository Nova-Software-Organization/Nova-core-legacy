CREATE TABLE pedido_item (
    idpedItem SERIAL PRIMARY KEY,
    idnumPed SERIAL,
    idproduto SERIAL,
    quantidade INT,
    precoUni NUMERIC,
    FOREIGN KEY (idnumPed) REFERENCES pedido(id_numero_pedido),
    FOREIGN KEY (idproduto) REFERENCES produto(idproduto)
);
