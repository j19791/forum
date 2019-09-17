package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizaTopicoForm;
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
	
	@Cacheable(value="listaDeTopicos") //o resultado do método é cacheavel e esse cache se chama listaDeTopicos (id p/ diferenciar de outros caches.
	@GetMapping //dados serão recebidos utilizando o metodo GET do HTTP 
	public Page<TopicoDTO> lista(//padrão DTO: nao retorna todos os atributos da entidade JPA
			@RequestParam(required = false) String nomeCurso, //parametro nao obrigatorio 
			@PageableDefault(sort="id", direction = Direction.DESC, page=0, size=10)  Pageable paginacao){ //criteiros de paginacao/ordenacao ja vem definidos pelo Spring (page&size&sort). Habilitar @EnableSpringDataWebSupport
		
		Page<Topico> topicos; //Page retorna os topicos e mais informações de paginação (numero de paginas, página atual, etc)
		
		if(nomeCurso == null) {
			topicos 
			= topicoRepository.findAll(paginacao); //Spring sabe agora que vc quer fazer paginacao com o resultado do findAll
			
		}
		else {//filtrando com valor passado por parametro na url. Nesse caso, pelo nome do curso: Curso.nome
			topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);//Spring sabe agora que vc quer fazer paginacao com o resultado do findByCurso_Nome
			
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
	public ResponseEntity<DetalhesDoTopicoDTO> detalhes(@PathVariable //vem da url 
										Long id) {
		
		//getOne retorna exceção 
		//Topico topico = topicoRepository.getOne(id);
		
		//se não encontrar, não retornar excption 
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {//se existe o recurso buscado
			
			return  ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get())); 
			
		}			
		return ResponseEntity.notFound().build(); //retorna 404		
		
	}
	
	
	
	@Transactional //spring faz commit no bd
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form){
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {//se existe o recurso buscado
			Topico topicoAtualizado = form.atualizar(id, topicoRepository);
		
			return ResponseEntity.ok(new TopicoDTO(topicoAtualizado)); //corpo no response
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {//se existe o recurso buscado
				topicoRepository.deleteById(id);
				return ResponseEntity.ok().build(); //volta apenas 200 pois o rescurso foi excluido
		}
		
		return ResponseEntity.notFound().build();	
		
	}
	
	
}
