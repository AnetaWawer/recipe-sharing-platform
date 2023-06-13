package net.azeti.challenge.recipe.security.config;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.security.jwt.JwtAuthentication;
import net.azeti.challenge.recipe.validation.CustomizedEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthentication jwtAuthentication;
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
                .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new CustomizedEntryPoint(HttpStatus.UNAUTHORIZED, "You need to be logged in to perform this action"))
                .and()
                .build();
    }
}
