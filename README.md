# Cadastro JDBC

Projeto em Java puro com JDBC para praticar CRUD com PostgreSQL.

Este projeto reúne:

- Modulo 29: CRUD completo de Cliente e Produto.
- Modulo 30 Parte 1: adicao dos campos `email` em Cliente e `marca` em Produto.

## Estrutura

```text
src/
  Cliente.java
  ClienteDAO.java
  ConnectionFactory.java
  IClienteDAO.java
  IProdutoDAO.java
  Main.java
  Produto.java
  ProdutoDAO.java
  schema.sql
```

## Banco de Dados

Crie o banco `cadastro` no PostgreSQL e execute o script:

```sql
src/schema.sql
```

Se as tabelas antigas ja existirem, rode tambem:

```sql
ALTER TABLE TB_CLIENTE ADD COLUMN IF NOT EXISTS EMAIL VARCHAR(100);
ALTER TABLE TB_PRODUTO ADD COLUMN IF NOT EXISTS MARCA VARCHAR(50);
```

## Configuracao

A classe `ConnectionFactory` usa estes valores padrao:

```text
URL: jdbc:postgresql://localhost:5432/cadastro
Usuario: postgres
Senha: postgres
```

Tambem e possivel configurar por variaveis de ambiente:

```text
DB_URL
DB_USER
DB_PASSWORD
```

Ou por propriedades da JVM:

```text
-Ddb.url=jdbc:postgresql://localhost:5432/cadastro
-Ddb.user=postgres
-Ddb.password=postgres
```

## Driver JDBC

Adicione o `.jar` do driver PostgreSQL nas dependencias do projeto no IntelliJ.

O arquivo `.jar` nao deve ser versionado no GitHub.

## Execucao

Rode a classe `Main`.

Ela executa um teste manual idempotente:

- remove registros de teste anteriores;
- cadastra Cliente e Produto;
- busca os registros;
- atualiza os campos;
- lista todos;
- exclui os registros de teste.
