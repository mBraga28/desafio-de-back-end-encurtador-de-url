<h1> Desafio de Back End: Encurtador de URLs - TDS Company 🚀 </h1>
# Desafio de Back End: Encurtador de URLs

Este projeto é um encurtador de URLs desenvolvido com Spring Boot, MongoDB e documentado usando Swagger Open API. Ele permite que você encurte URLs longas e rastreie estatísticas de acesso.

## Visão Geral

O objetivo deste projeto é criar um serviço que converta URLs originais em URLs curtas, facilitando o compartilhamento e o rastreamento de acessos.

## Funcionalidades

- Criação de URLs curtas a partir de URLs originais
- Redirecionamento de URLs curtas para URLs originais
- Estatísticas de acesso para URLs curtas

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Boot Web
- MongoDB
- Postman (para testar os endpoints)
- Bucket4j

## Estruturação do Projeto

O projeto segue a seguinte estrutura:

- `src/main/java/company/tds/urlshortener/`
    - `controllers/`: Controladores para lidar com as requisições HTTP
    - `dto/`: Objetos de transferência de dados (DTOs)
    - `entities/`: Entidades de modelo (por exemplo, `Url`)
    - `exceptions/`: Classes Customizadas para tratamento de Excessões e Erros 
    - `utils/`: Utilidades para limitação de taxa, utilizada para controlar a quantidade de solicitações recebidas por determinado período de tempo.
    - `repositories/`: Repositórios para interagir com o MongoDB
    - `services/`: Implementação da lógica de negócios

- `src/main/resources/`
    - `application.properties`: Configurações do MongoDB

## Sugestão para Rodar o Projeto

1. Clone o repositório para o seu ambiente local.
2. Configure o MongoDB (certifique-se de que ele esteja em execução na porta padrão).
3. Importe o projeto em sua IDE.
5. Inicialize o Docker Hub.
6. Execute o comando `docker-compose up`.
7. Execute a classe `Application` para iniciar o aplicativo Spring Boot.
8. Use o Postman para testar os endpoints.

## Link para os Testes dos Endpoints via Postman
- Uma collection do postman está na pasta raíz do projeto, use-a para consumir os endpoints.



