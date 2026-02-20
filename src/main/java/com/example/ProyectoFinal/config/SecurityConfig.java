package com.example.ProyectoFinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuración de seguridad de la aplicación
 * Implementa autenticación HTTP Basic con usuario en memoria
 * Protege los endpoints de la API mientras permite acceso a Swagger y H2 Console
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura el encoder de contraseñas usando BCrypt
     * BCrypt es un algoritmo de hash seguro para almacenar contraseñas
     * 
     * @return PasswordEncoder configurado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura un usuario en memoria para autenticación
     * Credenciales: admin / password123
     * 
     * @return UserDetailsService con usuario configurado
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    /**
     * Configura la cadena de filtros de seguridad
     * Define qué rutas están protegidas y cuáles son públicas
     * 
     * @param http Configuración de seguridad HTTP
     * @return SecurityFilterChain configurado
     * @throws Exception si hay un error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF ya que es una API REST stateless
            .csrf(csrf -> csrf.disable())
            
            // Configurar autorización de requests
            .authorizeHttpRequests(auth -> auth
                // Permitir acceso público a Swagger UI y API docs
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()
                
                // Permitir acceso a H2 Console (solo para desarrollo)
                .requestMatchers("/h2-console/**").permitAll()
                
                // Todos los endpoints de la API requieren autenticación
                .requestMatchers("/api/**").authenticated()
                
                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )
            
            // Habilitar autenticación HTTP Basic
            .httpBasic(withDefaults())
            
            // Configurar política de sesión como STATELESS (sin estado)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Configuración especial para H2 Console (permitir frames)
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
