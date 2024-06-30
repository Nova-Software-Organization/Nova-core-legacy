CREATE TABLE pedido_endereco (
    idendereco SERIAL PRIMARY KEY,
    rua VARCHAR NOT NULL,
    bairro VARCHAR NOT NULL,
    numCasa VARCHAR NOT NULL,
    estado VARCHAR,
    cep VARCHAR NOT NULL,
    numberOrder BIGINT,
    FOREIGN KEY (numberOrder) REFERENCES pedido(id_numero_pedido)
);
