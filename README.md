# Sobre
Este projeto foi desenvolvido durante o intensivão Java Spring, promovido pela [DevSuperior](https://devsuperior.com.br/), um Workshop que tem como objetivo aperfeiçoar e aprofundar os conhecimentos
dentro da linguagem Java ultilizando o framework SpringBoot.

# Projeto
- Objetivo
  
  > O projeto tem como propósito criar uma aplicação back-end em Java com o Framework SpringBoot versão 3.0.6, com end-points ultilizando a estruturação de projeto Spring RestT.
  > Bem como criar uma aplicação onde sejá possivel o usuário criar varias listas de games e dentro destas listas conseguir armazenar seus jogos preferidos por categoria de acordo com a sua preferência.
  > Mas também como desafio principal do projeto, teria que ser uma aplicação onde fosse possivel, que dentro de uma lista de jogos o usuário conseguisse mover um jogo e alterar a ordem dos jogos de acordo com
  > a sua vontade.
  
## Conceitos
  - Sistemas web e recursos
  - Cliente/servidor, HTTP, JSON
  - Padrão Rest para API web
  - Estruturação de projeto Spring Rest
  - Entidades e ORM
  - Database seeding
  - Padrão camadas
  - Deploy CI/CD, CORS
  - Consultas SQL no Spring Data JPA
  -  Projections

## Linguagens e Ferramentas
  - Java 17(LTS)
  - SprigBoot 3
  - Visual Studio Code
  - Postman
  - Docker
  - H2 Banco de Dados
  - Postgress

## Sistemas Web
![Imagem do WhatsApp de 2024-02-23 à(s) 18 38 46_7a74eb28](https://github.com/alexzxcg/dslist/assets/80990365/66066919-bcb7-4aa7-abd6-0ab8339038c1)

## API Rest
![Imagem do WhatsApp de 2024-02-23 à(s) 18 39 29_f2fbda0c](https://github.com/alexzxcg/dslist/assets/80990365/fff7bcef-73e1-4245-a965-cb578edbde62)

## Padrão de Camadas
![Imagem do WhatsApp de 2024-02-23 à(s) 18 40 12_1cc9eca4](https://github.com/alexzxcg/dslist/assets/80990365/194f8343-456b-41fb-b9ec-68e4e1261c52)

## End-Points
  - GET http://localhost:8080/lists
  - GET http://localhost:8080/lists/1/games
  - GET http://localhost:8080/lists/2/games
  - GET http://localhost:8080/game
  - GET http://localhost:8080/game/1
  - POST http://localhost:8080/lists/1/replacement
    ~~~~
    {
      "sourceIndex": 1,
      "destinationIndex": 4
    }


