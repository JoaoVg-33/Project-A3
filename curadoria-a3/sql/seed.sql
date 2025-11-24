USE curadoria_a3;
-- Atenção: inserir hash de senha na prática. Aqui usamos senha simples 'admin' como exemplo.
INSERT INTO users (username, name, age, role, password_hash, active)
VALUES (
  'admin',
  'Administrador do Sistema',
  30,
  'ADMIN',
  '$2a$10$0GZ77O8YoX/aBXtHk9KmeOBnDowTmxLxzLuJoVR31Wd5p5o9x5Eze', 
  1
)
