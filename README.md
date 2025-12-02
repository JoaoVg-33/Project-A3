# Safe Tecnolife – Sistema de Curadoria em Cibersegurança

Este é um **projeto desktop local**, desenvolvido em **Java (Swing)** utilizando **Maven**, com persistência de dados em **MySQL**.  
Não utiliza **Node.js**, **Express** ou qualquer tecnologia de backend web — tudo roda localmente na máquina do usuário.

---

## 🎯 Objetivo do Projeto

O sistema tem como objetivo permitir que usuários registrem e consultem recursos e materiais relacionados a:

- **Cibersegurança**  
- **IA Responsável**  
- **Privacidade & Ética Digital**

Além disso, o administrador pode gerenciar usuários e controlar o acesso ao sistema.

---

## 🖥️ Arquitetura do Sistema

A aplicação é composta por:

- Interface gráfica com **Java Swing**  
- Projeto gerenciado via **Maven**  
- Persistência em **MySQL**  
- Controle de acesso com dois tipos de usuários:
  - **Administrador**
  - **Usuário comum**

---

## 🔐 Fluxo Básico de Uso

1. **Login**  
2. Tela principal com:
   - **Admin:** gestão de usuários  
   - **Usuário comum:** cadastro e visualização de recursos  

---

## 📦 Tecnologias Utilizadas

- **Java 17+**  
- **Maven**  
- **Swing (javax.swing)**  
- **MySQL 8+**  
- **JDBC**  

---

## 📁 Estrutura do Projeto

- `/src/main/java` → Telas, classes de controle e conexão  
- `/src/main/resources` → SQL e configs  
- `pom.xml` → Dependências Maven  

---

## 🛢️ Banco de Dados

O MySQL contém tabelas para:

- Usuários  
- Recursos cadastrados  
- Interesses do usuário  

Um administrador padrão é criado com:

```
usuário: admin  
senha: admin123
```

---

## 🤝 Contribuição

Este projeto é acadêmico e pode ser modificado livremente.

---

## 📄 Licença

Uso educacional e demonstrativo.
