package edu.scoutsPoo.webApp.security;

import edu.scoutsPoo.webApp.services.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioService usuarioService;

    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioService) // UsuarioService debe implementar UserDetailsService
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // para pruebas en consola/Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuarios/registro", "/usuarios/login").permitAll()
                .requestMatchers("/scouts/**").hasAnyAuthority("SCOUT","ROVER","EDUCADOR")
                .requestMatchers("/actividades/**").hasAnyAuthority("ROVER","EDUCADOR")
                .requestMatchers("/grupos/**", "/comunidades/**", "/sedes/**").hasAuthority("EDUCADOR")
                .anyRequest().authenticated()
            )
            .httpBasic(); // autenticación básica para pruebas
        return http.build();
    }
}
