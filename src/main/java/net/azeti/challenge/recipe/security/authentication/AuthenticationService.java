package net.azeti.challenge.recipe.security.authentication;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.user.model.User;
import net.azeti.challenge.recipe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    public void register(Registration registration) {
        User user = User.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(registration.getPassword())
                .build();
        userRepository.save(user);
    }
}
