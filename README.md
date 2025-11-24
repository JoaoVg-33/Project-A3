Curadoria A3 — README
1. Introdução

O projeto Curadoria A3 tem como objetivo implementar um sistema completo para gerenciamento de itens em um acervo, incluindo funcionalidades de autenticação, controle de usuários, cadastro de obras e administração geral da plataforma. O sistema foi desenvolvido com foco em boas práticas de engenharia de software, modelagem de dados e integração entre backend, banco de dados e scripts de inicialização (seed).

Este documento apresenta instruções formais para instalação, configuração e execução do ambiente, além de descrever brevemente a estrutura do projeto.

2. Tecnologias Utilizadas

O sistema Curadoria A3 utiliza o seguinte conjunto de tecnologias:

Node.js — Ambiente de execução do backend.

Express.js — Framework para criação de APIs.

MySQL — Sistema gerenciador de banco de dados relacional.

Prisma ORM — Mapeamento objeto-relacional e ferramentas de migração.

BCrypt — Hash de senhas para segurança de credenciais.

dotenv — Gerenciamento de variáveis de ambiente.

3. Requisitos para Execução

Antes de iniciar o sistema, certifique-se de possuir:

Node.js (versão LTS)

MySQL Server e MySQL Workbench

NPM ou Yarn

Acesso para criação de schema no MySQL

4. Configuração do Banco de Dados
4.1 Criação do Schema

No MySQL Workbench, execute:

CREATE DATABASE curadoriaA3;

4.2 Configuração do Usuário

O sistema utiliza credenciais definidas no arquivo .env.
Exemplo:

DATABASE_URL="mysql://admin:senha@localhost:3306/curadoriaA3"


Certifique-se de que o usuário utilizado (ex.: admin) possui:

Permissão de acesso ao schema

Atributo Account Locked = OFF

Account expiration desativado

Autenticação por senha válida

5. Execução das Migrações e Seed
5.1 Instalar dependências
npm install

5.2 Aplicar migrações do Prisma
npx prisma migrate dev

5.3 Executar o seed
npx prisma db seed


O seed criará o usuário administrativo padrão, cujo hash de senha já está definido no script.

6. Estrutura do Projeto
/src
 ├── controllers/     → Lógica de controle das rotas
 ├── services/        → Regras de negócio
 ├── prisma/          → Schema, migrações e seed
 ├── routes/          → Definição de rotas da API
 └── server.js        → Inicialização do servidor

7. Execução do Sistema
7.1 Iniciar o servidor
npm start

7.2 Endpoints disponíveis

A API disponibiliza recursos para:

Autenticação

Gerenciamento de usuários

Inserção, consulta e atualização de itens do acervo

Controle administrativo

A documentação detalhada pode ser expandida em futuras versões do projeto.

8. Considerações Finais

O projeto Curadoria A3 foi desenvolvido com foco na aplicação prática de conceitos de modelagem de dados, segurança, arquitetura de software e persistência de informações. A estrutura modularizada facilita sua manutenção, evolução e adaptabilidade a demandas futuras.
