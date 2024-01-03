CREATE TABLE usuario_cliente (
    user_id BIGINT PRIMARY KEY,
    customer_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES usuario(user_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);
