package animal.crud.machadolabtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import animal.crud.machadolabtest.entity.Company;


public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}
