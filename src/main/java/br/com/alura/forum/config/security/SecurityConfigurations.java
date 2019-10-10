package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity //habilita nessa aplicação o módulo de segurança - default - tudo bloqueado
@Configuration //Spring configura a aplicação no startup a partir das definições dessas classes

public class SecurityConfigurations extends WebSecurityConfigurerAdapter {


	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//configura a autenticação (login)
		// TODO Auto-generated method stub
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//autorização: configura o que é público e o que precisa de controle de acesso
			
		//end points públicos: get da lista de tópicos e dos detalhes de um tópico
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll()
			.antMatchers(HttpMethod.GET, "/topicos/*").permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {//configurações de segurança dos recursos estáticos

	}
	
}
