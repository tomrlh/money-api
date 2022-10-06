package tomrlh.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomrlh.money.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}