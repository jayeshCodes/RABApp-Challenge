package animal.crud.machadolabtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import animal.crud.machadolabtest.entity.Movement;

public interface MovementRepository extends JpaRepository<Movement, Integer>{

}