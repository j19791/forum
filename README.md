# forum
Projeto do curso Spring Boot Parte 1: Contrua uma API Rest

## Passos para a criação do projeto
* [Gerador do Projeto - Spring Initializer](https://start.spring.io/)
* Project: Maven Project
* Language Java
* Versão 2.1.6
* Group: br.com.alura
* Artifact: forum
* Dependencies: Web
* Generate Project (zip)
* Descompactar zip
* Eclipse: Import / Existing Maven Projects

## Para rodar o projeto
* Na classe Main ForumApplication.java :  Run As > Java Application
* Pagina do projeto (http://localhost:8080/)

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

## Passando filtros
* com Spring Data, utilizar o padrão de nomeclatura findByAtributo(Tipo atributo) como metodo na interface Repository que o Spring ja criar o JPQL necessário p/ filtrar
* na url, filtrar com http://localhost:8080/topicos?nomeCurso=Spring+Boot
* se o valor possui espaço em branco, utilizar + no lugar do espaço

## Salvando dados na API
* Utilizar padrão form no lugar do padrão DTO ou da própria entidade
* Para trabalhar com JPA, utilizar sempre construtor padrão. Mas você pode criar novos construtores de necessário.
* Utilizar @PostMapping no lugar de @RequestMapping - que agora será anotada na classe
* Utilizar @RequestBody nos parametros do metodo cadastrar para indicar ao Spring que os dados estão vindo do corpo da requisição

##Códigos HTTP
* 200 - OK genérico
* 201 - OK: novo recurso criado com sucesso no servidor. Retornar URI e representação criada.

## Postman
* Navegador só dispara GET para o servidor na barra de endereços
* Para testar POST : utilizar o app POSTMAN do Chrome
![Post](https://github.com/j19791/forum/raw/master/Postman.jpg)


## Bean Validation
* Especificação p/ validar requests
* Incluir Nos atributos da classe que se deseja validar
* @NotNull
* @NotEmpty
* @Length(min=5)
* @Valid : No método do controller que recebe a requisição, colocar a anotação no parametro para que o Spring seja chamado e utilize o bean Validation de acordo com as anotações dos atributos  
* 400 - Bad Request - requisição invalida - dados não validados 

## Detalhes dos topicos
* GetMapping("{/id}")  passando registro especifico do q uma lista inteira
* @PathVariable : o id vem da url do request e passado como parametro do metodo do controlador.

## Atualizando Recursos
* @PutMapping("/{id}")
* Utilizar classe DTO especifica p/ o form com os campos q são permitidos alteração
 
## Excluindo recursos
* @DeleteMapping("/{id}")
* Não deverá retornar o recurso excluido, apenas o 200.
