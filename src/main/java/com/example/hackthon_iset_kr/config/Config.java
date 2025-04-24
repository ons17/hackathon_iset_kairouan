package com.example.hackthon_iset_kr.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public PasswordEncoder securityPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/adherents").permitAll()
                        .requestMatchers("/api/adherents/**").permitAll()
                        .requestMatchers("/api/adherents/{id}").permitAll()
                        .requestMatchers("/api/adherents/create").permitAll()
                        .requestMatchers("/api/adherents/login").permitAll()
                        .requestMatchers("/api/conventions/add").permitAll()
                        .requestMatchers("/api/conventions/id/**").permitAll()
                        .requestMatchers("/api/conventions").permitAll()

                        .requestMatchers("/api/admin/register", "/api/admin/login","/api/admin/update/**").permitAll()
                        .requestMatchers("/api/activities").permitAll()
                        .requestMatchers("/api/activities/{id}").permitAll()
                        .requestMatchers("/api/activities/create").permitAll()
                        .requestMatchers("/api/responsables").permitAll()
                        .requestMatchers("/api/events").permitAll()
                        .requestMatchers("/api/events/{id}").permitAll()
                        .requestMatchers("/api/events/create").permitAll()


                        .requestMatchers("/api/admin/register", "/api/admin/login", "/api/admin/update/**", "/api/admin/create-adherent").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}

