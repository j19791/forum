package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.token.TokenService;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenServico tokenService;
	
	
	//nao pode injetar o TokenServico pois AutenticacaoViaTokenFilter foi criada com new em SecurityConfigurations
	public AutenticacaoViaTokenFilter(TokenServico tokenService) {
		super();
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String token = recuperToken(request) ;
		
		boolean valido = tokenService.isTokenValido(token);
		
		System.out.println(valido);
		
		System.out.println(token);
		
		filterChain.doFilter(request, response); //segue o fluxo
		
	}
	
	public String recuperToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {			
			return null;			
		}
		
		return token.substring(7, token.length()); //sem a plavra Bearer
	}

}
