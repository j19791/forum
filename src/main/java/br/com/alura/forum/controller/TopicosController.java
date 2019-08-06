package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@RestController //Não precisa utilizar a anotação ResponseBody - todos os metodos retornam por padrão no corpo da resposta
public class TopicosController {

	@RequestMapping("/topicos") ////Endpoint p/ carregar uma lista de todos os topicos	
	public List<TopicoDTO> lista(){ //padrão DTO: nao retorna todos os atributos da entidade JPA
		
		Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
		
		return TopicoDTO.converter(Arrays.asList(topico, topico, topico));
		
		
		
		
		
	}
	
}
