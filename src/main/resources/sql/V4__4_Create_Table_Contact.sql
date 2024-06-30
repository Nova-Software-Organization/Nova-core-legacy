CREATE TABLE contato (
    idcontato SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    telefone VARCHAR(11),
    status INTEGER
);
