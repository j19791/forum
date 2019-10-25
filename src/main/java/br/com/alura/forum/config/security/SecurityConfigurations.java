package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //habilita nessa aplicação o módulo de segurança - default - tudo bloqueado
@Configuration //Spring configura a aplicação no startup a partir das definições dessas classes

public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService autenticacaoService; 
	
	@Autowired
	private TokenServico tokenService;
	
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
			.antMatchers(HttpMethod.POST,"/auth").permitAll() //liberar endpoint de autenticação			
			.anyRequest().authenticated() //restrição (requer autenticação): Para indicar que outras URLs que não foram configuradas devem ter acesso restrito
			//.and().formLogin(); //login fornecido agora é pela aplicação do liente
			.and().csrf().disable() //desativa tratamento p/ ataque csrf

			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//principio REST. Utilizar autenticação Stateless
			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class);//registra no Spring o filtro que intercepta o token dos requests
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {//configurações de segurança dos recursos estáticos

	}

	@Override 
	@Bean // Spring sabe que esse método devolve o authenticationManager e conseguimos injetar no AutenticacaoController
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	

	
	
	//encodar a senha 123456 utilizando algoritmo de encpriptação e salvar no bd
	/*public static void main (String[] args) {
		
		
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}*/
	
	
	
}
