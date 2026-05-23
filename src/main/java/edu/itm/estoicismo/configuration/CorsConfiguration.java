package edu.itm.estoicismo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración Global de CORS (Cross-Origin Resource Sharing).
 * Esta clase habilita el acceso a los endpoints (APIs) de Spring Boot
 * desde diferentes orígenes, lo cual es necesario cuando se tiene un
 * frontend web (ej. React, Angular) que accede a este backend.
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Aplica la configuración de CORS a todos los endpoints (/**) de tu API
        registry.addMapping("/**")
                // ** IMPORTANTE: Define aquí los orígenes permitidos. **
                // En producción, debes usar el dominio real de tu frontend.
                // El "*" es menos seguro, pero funciona para pruebas rápidas.
                .allowedOrigins(
                        "http://localhost:8091",
                        "http://localhost:8484",
                        "http://localhost:8485",
                        "*"
                )
                // Define los métodos HTTP que tu frontend puede usar
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permite todas las cabeceras
                .allowedHeaders("*")
                // No permite el envío de credenciales/cookies por defecto
                .allowCredentials(false)
                // Tiempo de cacheo para las peticiones preflight (OPTIONS)
                .maxAge(3600);
    }
}