package net.azeti.challenge.recipe.user.repository;

import net.azeti.challenge.recipe.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);
    Optional<User> findByUsername(String username);
}
