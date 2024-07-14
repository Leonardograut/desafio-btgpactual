<p align="center" width="100%">
    <img width="50%" src="https://github.com/buildrun-tech/buildrun-desafio-backend-btg-pactual/blob/main/images/btg-logo.jpg"> 
</p>

<h3 align="center">
  Desafio Backend do BTG Pactual
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">
</p>

## Desafio

Confira o enunciado completo do desafio [clicando aqui](./problem.md).

## Interação com o Banco de Dados

Utilizamos o MongoDB Compass para interagir com o banco de dados.

## Interação com a API

Utilizamos o Postman para interagir com a API.

## Tecnologias Utilizadas

- **Java 19**
- **Spring Boot**
- **Spring Data MongoDB**
- **RabbitMQ**
- **Docker**

## Como Rodar o Projeto

Siga os passos abaixo para rodar o projeto na sua máquina local:

### Pré-requisitos

- Docker instalado na sua máquina
- Java 17 instalado
- MongoDB Compass instalado

### Passo a Passo

1 Clone o repositório:
   ```
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
   
2 Navegue até o diretório do projeto:  
   ```
  cd seu-repositorio
   ```

3 Build o projeto utilizando o Maven:
  ```
  mvn clean install
  ```
4 Inicie os containers Docker:
  ```
  docker-compose up
  ```
