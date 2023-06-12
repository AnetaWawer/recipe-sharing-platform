package net.azeti.challenge.recipe.user.repository;

import net.azeti.challenge.recipe.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
