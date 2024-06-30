CREATE TABLE cliente (
    idcliente SERIAL PRIMARY KEY,
    idendereco SERIAL,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100),
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(20),
    idade INT CHECK (idade <= 150),
    genero VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    user_id SERIAL,
    date_criacao DATE,
    data_nascimento DATE,
    FOREIGN KEY (idendereco) REFERENCES endereco(idendereco),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);