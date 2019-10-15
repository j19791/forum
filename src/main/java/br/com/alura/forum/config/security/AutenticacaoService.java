package br.com.alura.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

@Service //classe vai ser uma classe gerenciada pelo Spring, só que não é um controller, não é um repository
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = repository.findByEmail(userName); 
		//para o Spring, ele só passa o e-mail e nós temos que fazer a consulta no banco de dados filtrando só por isso. A senha ele faz em memória.
		
		if(usuario.isPresent()) {//Se veio um usuário, é porque está certo o e-mail que você digitou
			
			return usuario.get();
			
		}
		
		throw new UsernameNotFoundException("Dados invalidos");//digitou um usuário que não existe no banco
		
	}

}
