package net.azeti.challenge.recipe.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/api/authentication/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/recipes/**").authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/recipes/**").authenticated()
                    .requestMatchers(HttpMethod.PUT, "/api/recipes/**").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/api/recipes/**").authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .build();
    }
}
