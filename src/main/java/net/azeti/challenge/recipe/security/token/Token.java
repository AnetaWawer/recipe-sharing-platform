package net.azeti.challenge.recipe.security.token;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String accessToken;
}
