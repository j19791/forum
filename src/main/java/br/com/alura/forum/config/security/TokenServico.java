package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServico {

	//Para injetar parâmetros do Application.properties. Recebe como parâmetro o nome da propriedade.
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	
	public String gerarToken(Authentication authentication) {
		
		Usuario logado = (Usuario) authentication.getPrincipal();
		
		
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));		
		
		return Jwts.builder()
				.setIssuer("Api do forum da Alura") //. Quem é que está gerando o token
				.setSubject(logado.getId().toString()) //quem é o dono desse token, quem é o usuário autenticado a quem esse token pertence
				.setIssuedAt(hoje) // data de geração do token. Quando ele foi concedido.
				.setExpiration(dataExpiracao) //data de validação, onde ele vai expirar, igual a sessão tradicional, para não ficar infinito, porque isso seria um risco de segurança
				.signWith(SignatureAlgorithm.HS256,
						secret) //Pela especificação JSON webtoken, o token tem que ser criptografado. Dizer quem é o algoritmo de criptografia e a senha da minha aplicação, que é usada para fazer a assinatura e gerar o REST da criptografia do token.
				.compact(); //compacta e transforma em String
		
	}


	public boolean isTokenValido(String token) {
		
		try {
			
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token); //se nao validar, retorna exception
			return true;
		}
		catch (Exception e) {
			return false;
		}
		
		
		
	}
	
}
