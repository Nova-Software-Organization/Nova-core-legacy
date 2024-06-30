CREATE TABLE midia (
    idmid SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP,
    categoria VARCHAR(255) NOT NULL
);
