package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenServico tokenService;
	
	private UsuarioRepository repository;
	
	
	//nao pode injetar o TokenServico pois AutenticacaoViaTokenFilter foi criada com new em SecurityConfigurations
	public AutenticacaoViaTokenFilter(TokenServico tokenService, UsuarioRepository repository) {
		super();
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String token = recuperToken(request) ;
		
		boolean valido = tokenService.isTokenValido(token);
		
		if(valido) {
			autenticarCliente(token);
		}
		
		System.out.println(valido);
		
		System.out.println(token);
		
		filterChain.doFilter(request, response); //segue o fluxo. Se não estiver autenticado, o Spring barra na frente
		
	}
	
	private void autenticarCliente(String token) {
		
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Usuario usuario = repository.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,  null, usuario.getAuthorities());
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);////Esse é o método para falar para o Spring considerar que o usuario está autenticado
		
		
		
		
		
	}

	public String recuperToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {			
			return null;			
		}
		
		return token.substring(7, token.length()); //sem a plavra Bearer
	}

}
