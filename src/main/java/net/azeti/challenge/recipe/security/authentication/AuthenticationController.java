package net.azeti.challenge.recipe.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Registration registration){
        if(authenticationService.isUsernameNotUnique(registration)){
            return ResponseEntity.status(409).body("There is already accounted registered on this username.");
        }
        if (authenticationService.isEmailNotUnique(registration)){
            return ResponseEntity.status(409).body("There is already accounted registered on this email.");
        }
        authenticationService.register(registration);
        return ResponseEntity.accepted().body("User account created.");
    }
}