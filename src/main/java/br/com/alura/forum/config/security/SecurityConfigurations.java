package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //habilita nessa aplicação o módulo de segurança - default - tudo bloqueado
@Configuration //Spring configura a aplicação no startup a partir das definições dessas classes

public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService autenticacaoService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//configura a autenticação (login)
		
		//diz para o Spring qual a classe (service) que tem a lógica de autenticação
		auth.userDetailsService(autenticacaoService)
			.passwordEncoder(new BCryptPasswordEncoder()); //algoritmo HASH seguro de decriptação de senhas
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//autorização: configura o que é público e o que precisa de controle de acesso
			
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll() //end points públicos: get da lista de tópicos e dos detalhes de um tópico
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll() //end points públicos: get da lista de tópicos e dos detalhes de um tópico
			.anyRequest().authenticated() //restrição (requer autenticação): Para indicar que outras URLs que não foram configuradas devem ter acesso restrito
			.and().formLogin(); //formulario de login fornecido pelo Spring
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {//configurações de segurança dos recursos estáticos

	}
	
	
	//encodar a senha 123456 utilizando algoritmo de encpriptação e salvar no bd
	/*public static void main (String[] args) {
		
		
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}*/
	
	
	
}
