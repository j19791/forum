package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void cadastrar(@RequestBody TopicoForm form) {//form é o padrão de dados recebidos do usuário
		
		
		Topico topico = form.converter(cursoRepository);
		
		topicoRepository.save(topico);
		
	}
	
}
