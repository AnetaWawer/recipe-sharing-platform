package net.azeti.challenge.recipe.security.authentication;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.user.model.User;
import net.azeti.challenge.recipe.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void register(Registration registration) {
        User user = User.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .build();
        userRepository.save(user);
    }
    public boolean isEmailNotUnique(Registration registration){
        return userRepository.existsUserByEmail(registration.getEmail());
    }
    public boolean isUsernameNotUnique(Registration registration){
        return userRepository.existsUserByUsername(registration.getUsername());
    }
}
