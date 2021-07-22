package cl.vampfern.ejercicio13.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findUserEntityByEmail(String email);

}
