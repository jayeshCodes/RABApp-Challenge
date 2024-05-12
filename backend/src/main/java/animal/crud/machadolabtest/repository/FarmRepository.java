package animal.crud.machadolabtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import animal.crud.machadolabtest.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, String> {

} 