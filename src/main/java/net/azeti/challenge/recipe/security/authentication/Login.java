package net.azeti.challenge.recipe.security.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
