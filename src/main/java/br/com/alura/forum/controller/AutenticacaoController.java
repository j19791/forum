package br.com.alura.forum.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenServico;
import br.com.alura.forum.controller.dto.TokenDto;
import br.com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	
	@Autowired
	private TokenServico tokenService;
	
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar (@RequestBody @Valid LoginForm form){
		
		//O método authenticate recebe um objeto  do tipo userNamePasswordAuthenticationToken.
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
			
			Authentication authentication =  authManager.authenticate(dadosLogin);//para fazer a autenticação, preciso passar para ele os dados de login
			
			String token = tokenService.gerarToken(authentication);	//Quero devolver o token. fazer a geração do token e guardá-lo em uma string. vou precisar identificar para qual usuário pertence aquele token.	
			
			//devolver um 200 e levar esse token junto como resposta. No body da response, foi devolvido o token e o tipo. Esse token vai voltar para o cliente, responsável por guardar em algum lugar. Nas próximas requisições que ele disparar, ele vai ter que levar esse token a um cabeçalho autorization: responsável por cuidar da parte de autorização. Dizer no cabeçalho qual o tipo de autenticação.
			return ResponseEntity.ok(new TokenDto(token, 
					"Bearer")); //tipo de autenticação: como ele vai fazer a autenticação nas próximas requisições
			
			
			
			
		} catch (AuthenticationException e) {//usuario e senha incorretos
			return ResponseEntity.badRequest().build();//erro 400
		}
		

		
		
		
	}
	
	
}
