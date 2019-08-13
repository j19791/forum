package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.alura.forum.modelo.Topico;

//padrão que substitui o ADO - metodos CRUDs
//interface nao precisa de anotação - o Spring ja acha automaticamente
public interface TopicoRepository extends JpaRepository<Topico, 
														Long //tipo da chave primaria
														> {
	

	List<Topico> findByCurso_Nome(String nomeCurso);//utilizando o padrao de nomeclatura, não precisa de JPQL. Exemplo de composição
	
	List<Topico> findByTitulo(String titulo);//procurando por titulo do topico (atributo da entidade Topico)
	
	//personalizando nome do metodo
	@Query("select t from Topico t where t.titulo = :titulo") //obrigatorio usar JPQL nesse caso
	List<Topico> procurarPorTitulo(@Param("titulo") String titulo);
														

}
