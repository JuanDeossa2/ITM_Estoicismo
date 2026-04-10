package edu.itm.ejemplo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new io.swagger.v3.oas.models.info.Info()
                .title(" Desarrollo Integral y Estoicismo")
                .version("0.0.1")
                .description("Proyecto de Desarrollo Integral y Estoicismo")
                .contact(new Contact().name("Juan Pablo").email("juandeossa1128281@correo.itm.edu.co")));
    }
}
