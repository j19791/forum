package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	
	@ResponseBody // o Spring considera que o retorno do método é o nome da página que ele deve carregar. A anotação @ResponseBody indica que o retorno do método deve ser serializado e devolvido no corpo da resposta.
	@RequestMapping("/") //pagina raiz do projeto: http://localhost:8080/
	public String helloController() {
		
		return "Hello World";
		
	}

}
