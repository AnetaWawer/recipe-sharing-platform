package net.azeti.challenge.recipe.security.authentication.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.security.authentication.model.Login;
import net.azeti.challenge.recipe.security.authentication.model.Registration;
import net.azeti.challenge.recipe.security.jwt.JwtService;
import net.azeti.challenge.recipe.security.token.Token;
import net.azeti.challenge.recipe.user.model.User;
import net.azeti.challenge.recipe.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public void register(Registration registration) {
        User user = User.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        userRepository.save(user);
    }
    public Token login(Login loginDetails){
        Authentication authenticationData = new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword());
        authenticationManager.authenticate(authenticationData);
        User user = userRepository.findByUsername(loginDetails.getUsername()).orElseThrow();
        String jwt = jwtService.buildToken(user);
        return Token.builder()
                .accessToken(jwt)
                .build();
    }
    public boolean isEmailNotUnique(Registration registration){
        return userRepository.existsUserByEmail(registration.getEmail());
    }
    public boolean isUsernameNotUnique(Registration registration){
        return userRepository.existsUserByUsername(registration.getUsername());
    }
}
