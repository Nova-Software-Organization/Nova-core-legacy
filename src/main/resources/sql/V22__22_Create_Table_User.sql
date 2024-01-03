CREATE TABLE usuario (
    user_id SERIAL PRIMARY KEY,
    apelido VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    token_redefinicao_senha VARCHAR(255),
    expiracao_token_redefinicao_senha TIMESTAMP,
    roles VARCHAR(255) ARRAY,
    CONSTRAINT check_roles CHECK (roles IS NULL OR array_length(roles, 1) = 1),
    CONSTRAINT check_roles_values CHECK (roles IS NULL OR roles[1] IN ('ADMIN', 'USER'))
);