package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
