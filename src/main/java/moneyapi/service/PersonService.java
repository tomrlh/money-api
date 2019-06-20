package moneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import moneyapi.model.Person;
import moneyapi.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	
	
	public Person update(Long id, Person person) {
		Optional<Person> personToUpdate = personRepository.findById(id);
		if(personToUpdate.isPresent()) {
			BeanUtils.copyProperties(person, personToUpdate.get(), "id");
			return personRepository.save(personToUpdate.get()); 
		}
		else
			throw new EmptyResultDataAccessException(1);
	}
}
