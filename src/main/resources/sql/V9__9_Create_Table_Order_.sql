CREATE TABLE pedido (
    id_numero_pedido SERIAL PRIMARY KEY,
    idcliente SERIAL,
    email VARCHAR,
    status VARCHAR,
    dtpagamento DATE,
    nmcliente VARCHAR,
    valorTotal FLOAT,
    metodo_pagamento VARCHAR,
    idtransacao SERIAL,
    FOREIGN KEY (idcliente) REFERENCES cliente(idcliente),
    FOREIGN KEY (idtransacao) REFERENCES transacao(idtransacao)
);