# Produto com JPA

Mini projeto para verificar a criacao automatica de tabela com JPA e Hibernate.

## O que foi mapeado

A classe `Produto` possui tres propriedades de negocio:

- `codigo`
- `nome`
- `preco`

Tambem possui o campo tecnico `id`, usado como chave primaria.

## Banco

O projeto usa o banco PostgreSQL:

```text
jdbc:postgresql://localhost:5432/cadastro
usuario: postgres
senha: postgres
```

Ao rodar `MainJPA`, o Hibernate le o arquivo `persistence.xml` e cria ou atualiza a tabela:

```text
TB_PRODUTO_JPA
```

## Como executar

No IntelliJ, abra esta pasta como projeto Maven ou importe o `pom.xml`.

Depois rode a classe:

```text
MainJPA
```

Se o banco estiver rodando e as credenciais estiverem corretas, o Hibernate deve mostrar o SQL no console e criar/verificar a tabela automaticamente.
