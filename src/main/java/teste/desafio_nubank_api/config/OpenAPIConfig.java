package teste.desafio_nubank_api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                .info(new Info()
                    .title("Desafio Nubank")
                    .description("Desafio para estudo de Java-Spring Boot")
                    .version("v1.0.0")
                    .contact(new Contact()
                        .name("Adilson Jr.")
                        .email("dilsjr@gmail.com"))
                    .license(new License()
                    .name("Apache 2.0")
                    .url("https://")))
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor Local")))
                .externalDocs(new ExternalDocumentation().description("Documentação do projeto").url("github"));
                

                    
                
    }

}
