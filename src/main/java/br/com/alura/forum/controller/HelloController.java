package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@ResponseBody // Para o Spring não procurar para JSP no projeto. Considera o retorna do metodo
	@RequestMapping("/") //pagina raiz do projeto: http://localhost:8080/
	public String helloController() {
		
		return "Hello World";
		
	}

}
