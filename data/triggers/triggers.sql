-- Trigger para registrar novo cadastro

CREATE TABLE auditoria (acao VARCHAR(30), tabela VARCHAR(30), momento DATE);

CREATE TRIGGER registerNewCustomer AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (acao, tabela, momento)
    VALUES ('Novo Cadastro', 'usuario ' || NEW.username, NOW());
END;

-- Trigger para registrar login
CREATE TRIGGER loginCustomer AFTER INSERT ON logins
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (acao, tabela, momento)
    VALUES ('Login', 'usuario ' || NEW.username, NOW());
END;
