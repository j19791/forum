package br.com.alura.forum.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
public class Usuario 
	implements UserDetails //interface para dizer ao Spring que essa é a classe que tem detalhes de um usuário. 
	{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	@ManyToMany (fetch = FetchType.EAGER) // um usuário pode ter vários perfis e um perfil pode ter vários usuários
	private List<Perfil> perfis = new ArrayList<>(); // Qual o perfil relacionado com as permissões de acesso dele.

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;//informar p/ o Spring o de/para do nosso modelo com o passowrd do controle de acesso
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.nome; //informar p/ o Spring o de/para do nosso modelo com o username do controle de acesso
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true; //true pois não se deve preocupar com isso agora
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}



}
