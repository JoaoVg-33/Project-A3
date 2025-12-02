Safe Tecnolife - Projeto para Faculdade (NetBeans)
===================================================

Conteúdo:
- src/safe_tecnolife/*.java  (código Java com telas Swing simples e cores leves)
- sql/safe_tecnolife.sql    (script para criar DB e inserir usuário admin e exemplo)
- docs/uml.txt              (UML simples em texto)
- nbproject/*               (configuração NetBeans)

Como usar:
1. Importe o projeto no NetBeans: File > Open Project > selecione a pasta Safe_Tecnolife_NetBeans_Faculdade
2. Adicione o MySQL Connector/J nas bibliotecas do projeto (Tools > Libraries)
3. Execute o script SQL em seu MySQL Workbench: sql/safe_tecnolife.sql
4. Ajuste Config.DB_USER/DB_PASS se necessário
5. Execute o projeto (F6).

Usuário administrador padrão:
- ID: 1
- Nome: admin
- Senha: admin123

Observações:
- Layout simples com cores leves conforme solicitado.
- Login por ID / Nome / Senha.
- Admin tem acesso à gestão de usuários.
- Usuário comum pode cadastrar e listar recursos (ordenados por título).