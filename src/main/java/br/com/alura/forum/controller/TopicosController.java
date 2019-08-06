package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;

@Controller
public class TopicosController {

	@RequestMapping("/topicos") ////Endpoint p/ carregar uma lista de todos os topicos
	@ResponseBody
	public List<Topico> lista(){ 
		
		Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
		
		return Arrays.asList(topico, topico, topico);//retorna uma lista com 3 elementos com o mesmo objeto topico
		
		
	}
	
}
