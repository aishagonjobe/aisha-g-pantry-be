package net.yorksolutions.aishagpantrybe.repository;

import net.yorksolutions.aishagpantrybe.model.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<Auth, Long> {
    Optional<Auth> findByUsernameAndPassword(String username, String password);

    Boolean existsByUsername(String username);

}
