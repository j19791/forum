package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.modelo.Topico;

//padrão que substitui o ADO - metodos CRUDs
//interface nao precisa de anotação - o Spring ja acha automaticamente
public interface TopicoRepository extends JpaRepository<Topico, 
														Long //tipo da chave primaria
														> {

}
