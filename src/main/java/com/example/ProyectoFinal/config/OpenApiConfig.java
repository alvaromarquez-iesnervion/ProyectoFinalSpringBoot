package com.example.ProyectoFinal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger para documentación de la API
 * Define información general de la API y esquema de seguridad
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Alumnos",
        version = "1.0.0",
        description = "API REST completa para la gestión de estudiantes. " +
                     "Incluye operaciones CRUD, búsqueda, paginación y autenticación básica. " +
                     "Desarrollada con Spring Boot, JPA, Spring Security y documentación Swagger.",
        contact = @Contact(
            name = "Equipo de Desarrollo",
            email = "desarrollo@ejemplo.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    servers = {
        @Server(
            description = "Servidor Local",
            url = "http://localhost:8080"
        )
    }
)
@SecurityScheme(
    name = "basicAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "basic",
    description = "Autenticación HTTP Basic. Credenciales: admin / password123"
)
public class OpenApiConfig {
    // Esta clase solo necesita las anotaciones
    // Spring Boot configurará automáticamente Swagger UI basándose en ellas
}
