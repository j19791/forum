package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching //habilita o cache na aplicação
@EnableSpringDataWebSupport //habilita a passagem de parametros de ordenação/paginacao pela URL utilizando os criterios do Spring
@EnableSwagger2 //necessário para gerar habilitar a geração da documentação atomatizada
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}



