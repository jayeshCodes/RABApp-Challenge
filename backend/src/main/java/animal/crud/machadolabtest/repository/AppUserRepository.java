package animal.crud.machadolabtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import animal.crud.machadolabtest.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsByEmail(String email);

    Optional<AppUser> findByEmail(String email);
}
