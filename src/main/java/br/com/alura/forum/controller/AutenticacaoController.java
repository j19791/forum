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
import br.com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	
	@Autowired
	private TokenServico tokenService;
	
	
	@PostMapping
	public ResponseEntity<?> autenticar (@RequestBody @Valid LoginForm form){
		
		//O método authenticate recebe um objeto  do tipo userNamePasswordAuthenticationToken.
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			
			
			Authentication authentication =  authManager.authenticate(dadosLogin);//para fazer a autenticação, preciso passar para ele os dados de login
			
			String token = tokenService.gerarToken(authentication);	//Quero devolver o token. fazer a geração do token e guardá-lo em uma string. vou precisar identificar para qual usuário pertence aquele token.	
			
			return ResponseEntity.ok().build();
			
		} catch (AuthenticationException e) {//usuario e senha incorretos
			return ResponseEntity.badRequest().build();//erro 400
		}
		

		
		
		
	}
	
	
}
