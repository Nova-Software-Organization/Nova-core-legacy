 CREATE TABLE fornecedor (
    idfornecedor SERIAL PRIMARY KEY,
    nm_empresa VARCHAR,
    regiao VARCHAR,
    cargo_fornecedor VARCHAR,
    dt_criacao DATE,
    contato VARCHAR,
    cnpj VARCHAR,
    status INT,
    id_ed_fornecedor SERIAL,
    FOREIGN KEY (id_ed_fornecedor) REFERENCES endereco_fornecedor(id_ed_fornecedor)
);