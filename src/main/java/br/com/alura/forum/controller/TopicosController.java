package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RequestMapping("/topicos")
@RestController //Não precisa utilizar a anotação ResponseBody - todos os metodos retornam por padrão no corpo da resposta
public class TopicosController {

	
	@Autowired //injetando dependencia
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping //dados serão recebidos utilizando o metodo GET do HTTP 
	public List<TopicoDTO> lista(String nomeCurso){ //padrão DTO: nao retorna todos os atributos da entidade JPA
		
		//Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
		
		List<Topico> topicos;
		
		if(nomeCurso == null) {
			topicos = topicoRepository.findAll();
			
		}
		else {//filtrando com valor passado por parametro na url. Nesse caso, pelo nome do curso: Curso.nome
			topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			
		}
		
		
		return TopicoDTO.converter(topicos);
		
		
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody 
			@Valid //avisa o spring p/ utilizar a especificação Bean Validation e as anotações dos atributos da classe TopicoForm 
			TopicoForm form, //form é o padrão de dados recebidos do usuário
			UriComponentsBuilder uriBuilder) { //p/ construir a URI (obrigatório p/ retornar 201
		
		
		Topico topico = form.converter(cursoRepository);
		
		topicoRepository.save(topico);
		//qdo o metodo retorna void, retorna código http 200 (OK genérico)
		
		//codigo 201 - Ok - novo recurso cadastrado com sucesso
		//path (p/ nao utilizar localhost:8080 e sim o endereço do siste)
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
		
	}
	
	
	@GetMapping("/{id}") //o id na url passada no request é dinamico
	public DetalhesDoTopicoDTO detalhes(@PathVariable //vem da url 
										Long id) {
		
		Topico topico = topicoRepository.getOne(id);
		
		
		
		
		return new DetalhesDoTopicoDTO(topico);
				
		
	}
	
}
