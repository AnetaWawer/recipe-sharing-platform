package net.azeti.challenge.recipe.user.repository;

import net.azeti.challenge.recipe.security.authentication.Login;
import net.azeti.challenge.recipe.security.authentication.Registration;
import net.azeti.challenge.recipe.security.authentication.RegistrationResult;
import net.azeti.challenge.recipe.security.token.Token;

import java.util.Optional;

public interface UserManagement {

    RegistrationResult register(Registration registration);

    Optional<Token> login(Login login);
}
