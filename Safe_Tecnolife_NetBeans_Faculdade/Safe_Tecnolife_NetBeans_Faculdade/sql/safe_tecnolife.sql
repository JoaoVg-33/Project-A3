-- safe_tecnolife.sql (para MySQL Workbench)
CREATE DATABASE IF NOT EXISTS safe_tecnolife;
USE safe_tecnolife;

CREATE TABLE IF NOT EXISTS usuarios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(80) NOT NULL,
  idade INT,
  senha VARCHAR(100) NOT NULL,
  tipo ENUM('ADMIN','USUARIO') NOT NULL,
  interest1 VARCHAR(80),
  interest2 VARCHAR(80),
  ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS recursos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(120) NOT NULL,
  autor VARCHAR(80),
  categoria VARCHAR(80) NOT NULL,
  usuario_id INT,
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Usuário admin padrão
INSERT INTO usuarios (nome,idade,senha,tipo,ativo) VALUES ('admin',30,'admin123','ADMIN',1);

-- Exemplo de usuário comum
INSERT INTO usuarios (nome,idade,senha,tipo,interest1,interest2,ativo) VALUES ('usuario1',25,'user123','USUARIO','IA RESPONSAVEL','CIBERSEGURANCA',1);

-- Exemplo de recurso
INSERT INTO recursos (titulo,autor,categoria,usuario_id) VALUES ('Introdução a IA Responsável','Autor X','IA RESPONSAVEL',2);