package com.novaBankpractice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Desactivamos CSRF porque en APIs REST con tokens no se usa
                .authorizeHttpRequests(auth -> auth
                        // Definimos qué rutas son PÚBLICAS (No necesitan token)
                        .requestMatchers("/api/auth/**").permitAll() // Aquí pondremos el Login más adelante
                        .requestMatchers("/test/**").permitAll() // Tu TestController de utilidad queda abierto
                        // Cualquier otra petición necesita estar AUTENTICADA (Necesita token válido)
                        .anyRequest().authenticated()
                )
                // Le decimos a Spring que no guarde sesiones (Estado) en memoria, porque cada petición traerá su token (Stateless)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Por último, metemos a nuestro "portero" (JwtFilter) justo antes de que Spring intente pedir usuario/contraseña
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("****")
                .password("***************") // Usuario y Contraseña no disponibles
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    // 2. Activamos el Gestor de Autenticación de Spring
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
