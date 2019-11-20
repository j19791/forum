package br.com.alura.forum.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.modelo.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum")) //a geração da doc é a partir desse pacote
                .paths(PathSelectors.ant("/**")) //sem restrições de end points
                .build()
                .ignoredParameterTypes(Usuario.class) //protege de exibir senha do usuário, por exemplo
                //parâmetro global que o Swagger apresente em todos os endpoints.
                .globalOperationParameters( //configurar um campo para conseguir digitar o cabeçalho do authorization e digitar o token.
                        Arrays.asList(
                                new ParameterBuilder()
                                    .name("Authorization") //nome do parametro
                                    .description("Header para Token JWT") //descrição para aparecer no swagger-ui
                                    .modelRef(new ModelRef("string")) //o token é um string
                                    .parameterType("header") //tipo do parametro: cabeçalho
                                    .required(false) //parametro Authorization é opcional: tem uri q não precisa dele
                                    .build()));
    }

	
}
