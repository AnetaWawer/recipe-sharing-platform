package net.azeti.challenge.recipe.security.authentication.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Registration {
    private String email;
    private String username;
    private String password;
}
