USE curadoria_a3;
-- Atenção: inserir hash de senha na prática. Aqui usamos senha simples 'admin' como exemplo.
INSERT INTO users (name, age, username, password_hash, role) VALUES
('Admin Inicial', 30, 'admin', '$2a$10$7Qy/ExampleHashReplaceMe1234567890abcdefg', 'ADMIN');
