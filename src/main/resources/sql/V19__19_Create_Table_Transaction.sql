CREATE TABLE transacao (
    idtransacao SERIAL PRIMARY KEY,
    cardNum VARCHAR(255),
    cpf VARCHAR(14) CHECK (cpf SIMILAR TO '[0-9]{11}'),
    dtCompra TIMESTAMP,
    status VARCHAR(255),
    valorTotal NUMERIC,
    parcela VARCHAR(255)
);
