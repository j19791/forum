# forum
Projeto do curso Spring Boot Parte 1: Contrua uma API Rest

## Passos para a criação do projeto

[Gerador do Projeto - Spring Initializer](https://start.spring.io/)
Project: Maven Project

Language Java

Versão 2.1.6

Group: br.com.alura

Artifact: forum

Dependencies: Web

Generate Project (zip)

Descompactar zip

Eclipse: Import / Existing Maven Projects


## Para rodar o projeto

Na classe Main ForumApplication.java :  Run As > Java Application

Pagina do projeto (http://localhost:8080/)


## Dependencia Devtools

Permite reinicialização automatica do servidor sem necessidade de reinicia-lo ao salvar as mudanças

## Padrão DTO

Utilizando JPA, não se deve retornar todos os atributos de um,a entidade JPA. Com DTO, você escolhe exatamente os atributos da classe que você deseja retornar na resposta

## REST

* Representational State Transfer
* Modelo de Arquitetura para sistemas distribuidos
* Utiliza o protocolo HTTP
* Evolução do modelo SOAP (xml)
* Recursos: o que a aplicação gerencia - Alunos, tópicos, respostas, cursos
* URI: identificação única dos recursos: /alunos, /topicos, /respostas
* Verbos: manipulação dos recursos: GET /alunos POST /alunos PUT /alunos{id} 
* Representação dos recursos : media type : XML, JSON
* Stateless: não guarda sessão - não armazena dados dos usuários p/ que o protocolo tenha escalabilidade

## Padrão Repository

* Substitui o padrão ADO. Não prcisa criar métodos CRUD isolados em classes xxxxADO
* criar uma interface que extender JpaRepository<T,> e injetar na classe q vc quer usar
