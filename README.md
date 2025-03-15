# Backend Fenix

Projeto de demonstração utilizando Spring Boot.

## Descrição

Este projeto é uma aplicação backend desenvolvida com Spring Boot, configurada para utilizar diversas dependências e ferramentas modernas para desenvolvimento de APIs e serviços.

## Requisitos

- **Java 17**: Certifique-se de ter o Java 17 instalado.
- **Maven**: Necessário para gerenciar as dependências e o ciclo de vida do projeto.

## Dependências Principais

- **Spring Boot Starter Web**: Para criação de APIs REST.
- **Spring Boot Starter Data JPA**: Para integração com bancos de dados utilizando JPA.
- **Spring Boot Starter Security**: Para segurança da aplicação.
- **Spring Boot Starter OAuth2 Resource Server**: Para suporte a OAuth2.
- **Spring Boot Starter Validation**: Para validação de dados.
- **MySQL Connector**: Driver para conexão com banco de dados MySQL.
- **SpringDoc OpenAPI**: Para documentação da API com Swagger.
- **Jackson Datatype JSR310**: Suporte para tipos de data e hora do Java 8+.
- **Nimbus JOSE + JWT**: Para manipulação de tokens JWT.
- **Dotenv Java**: Para gerenciamento de variáveis de ambiente.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/josegonzalezmonroy/fenix-back.git
   cd fenix-back
   ```

2. Configure as variáveis de ambiente necessárias no arquivo `.env`.

3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação em `http://localhost:8080`.

## Inicialização de Dados

- O projeto já vem configurado com um arquivo `data.sql` localizado na pasta `src/main/resources`. Este arquivo é executado automaticamente ao iniciar a aplicação, populando o banco de dados com dados iniciais.

- Além disso, o sistema verifica se o usuário administrador (`admin@admin.com`) já existe. Caso contrário, ele será criado automaticamente com as seguintes credenciais:
  - **Email**: `admin@admin.com`
  - **Senha**: `123456`
  - **Perfil**: `ADMIN`

## Documentação da API

A documentação da API está disponível em:
```
http://localhost:8080/swagger-ui.html
```

## Licença

Este projeto não possui uma licença definida no momento.

## Desenvolvedores

Este projeto foi desenvolvido por:
- **[Jose Gonzalez Monroy]**

