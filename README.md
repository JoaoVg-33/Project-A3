📌 Descrição Geral do Projeto

O Curadoria A3 é um projeto acadêmico desenvolvido como uma aplicação desktop local, implementada em Java (JDK 11+) utilizando Swing para a interface gráfica.
O sistema não utiliza Node.js, Express, nem quaisquer tecnologias de back-end web.
Toda a lógica da aplicação é executada localmente na máquina do usuário.

O objetivo do sistema é fornecer uma plataforma simples para gerenciamento de usuários e recursos digitais, com funcionalidades de autenticação, administração e cadastro de conteúdos relacionados a temas como IA responsável, cibersegurança e privacidade.

🖥️ Tecnologias Utilizadas

Java 11+

Swing (GUI Desktop)

Maven como gerenciador de dependências

MySQL para persistência dos dados

JDBC (MySQL Connector/J) para comunicação com o banco

❗ Não são utilizadas tecnologias web como Node.js, Express, JavaScript no back-end, APIs REST ou servidores externos.
Toda a aplicação roda de forma 100% local.

📁 Arquitetura e Estrutura

O projeto utiliza uma estrutura simplificada, com todas as telas e lógica concentradas em um único arquivo Java principal (Main.java), para fins de estudo e organização direcionada à atividade acadêmica.
{
src/
 └── main/
      └── java/
           └── app/
                └── Main.java   <- Todas as telas (Login, Admin, Usuário, Diálogos)
sql/
 └── safetecnolife_schema.sql   <- Script de criação do banco de dados
pom.xml                          <- Configuração Maven
README.md
}

🗄️ Banco de Dados

A aplicação utiliza um banco MySQL local chamado:

{safetecnolife}


O script para criar o schema, tabelas e dados iniciais está localizado em:

{/sql/safetecnolife_schema.sql}


Basta executá-lo no MySQL Workbench ou terminal antes de rodar o sistema.

🚀 Como Executar o Projeto
1. Criar o Banco de Dados

Execute o conteúdo do arquivo:

{sql/safetecnolife_schema.sql}


Isso criará as tabelas, relações e usuários iniciais.

2. Ajustar configurações de conexão

No arquivo Main.java, dentro da classe interna DBUtil, ajuste se necessário:
{
public static final String DB_URL = "jdbc:mysql://localhost:3306/safetecnolife";
public static final String DB_USER = "root";
public static final String DB_PASS = "sua_senha";
}

3. Compilar e Executar com Maven

Para gerar o JAR:

mvn clean package


O JAR executável ficará em:

target/safe-tecnolife-1.0.1-shaded.jar


Para executar:

java -jar target/safe-tecnolife-1.0.1-shaded.jar

👤 Usuários iniciais (Seed)

Admin

Usuário: admin

Senha: admin123

Usuário comum

Usuário: user1

Senha: user123

📚 Funcionalidades
✔ Login com diferenciação de perfis
✔ Painel de administrador

Cadastro de usuários

Edição de usuários

Ativação/Inativação

Visualização da lista completa

✔ Painel de usuário

Cadastro de recursos

Listagem dos recursos criados

Categorias temáticas

🎯 Objetivo Acadêmico

Este projeto foi construído com foco em:

prática de Java Desktop

manipulação de MySQL via JDBC

desenvolvimento de interface gráfica com Swing

compreensão de modelos CRUD em um ambiente local

arquitetura simplificada para fins de didática

📄 Observações Finais

O sistema não depende de serviços externos.

Todo processamento ocorre localmente.

A estrutura foi simplificada para fins de avaliação acadêmica.

Se quiser uma versão mais curta, uma versão para impressão, ou um README mais técnico, posso gerar também!
