package br.com.alura.forum.controller.dto;


//DTO: padrão de criar um dto e dentro do dto encapsular as informações
public class TokenDto {

	private String token;
	
	private String tipo;

	public TokenDto(String token, String tipo) {
		super();
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
	
	
}
