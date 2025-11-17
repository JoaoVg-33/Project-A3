# Curadoria A3 - Sistema de Curadoria (Java Swing + MySQL)

Projeto exemplo para a disciplina — aplicação desktop em Java (Swing) com persistência MySQL.

## Conteúdo
- Código fonte Java (Maven)
- Script SQL (schema + seed com admin)
- Exemplo de config.properties para conexão
- Instruções para enviar ao GitHub **sem usar gitbash**

## Como rodar
1. Configure o MySQL e execute `sql/schema.sql` e `sql/seed.sql`.
2. Ajuste `src/main/resources/config.properties` com suas credenciais.
3. No terminal da IDE: `mvn clean compile exec:java -Dexec.mainClass="app.Main"`
   ou execute `app.Main` pela sua IDE.

## Como subir para o GitHub sem gitbash
- Pelo site do GitHub: crie um repo e use "Add file" → "Upload files".
- Pelo GitHub Desktop: clone (ou criar) e arraste a pasta do projeto para o GitHub Desktop e faça commit/sync.

Arquivo gerado automaticamente pelo assistente.
