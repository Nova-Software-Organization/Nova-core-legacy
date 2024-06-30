CREATE TABLE endereco (
    idendereco BIGINT AUTO_INCREMENT PRIMARY KEY,
    idcliente BIGINT,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    numCasa VARCHAR(255) NOT NULL,
    estado VARCHAR(255),
    cep VARCHAR(20) NOT NULL,
    FOREIGN KEY (idcliente) REFERENCES cliente(idcliente)
);
