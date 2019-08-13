package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

@RestController //Não precisa utilizar a anotação ResponseBody - todos os metodos retornam por padrão no corpo da resposta
public class TopicosController {

	
	@Autowired //injetando dependencia
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos") ////Endpoint p/ carregar uma lista de todos os topicos	
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
	
}
