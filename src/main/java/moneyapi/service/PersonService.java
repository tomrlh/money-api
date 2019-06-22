package moneyapi.service;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

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
	
	
	
	public Person findById(Long id) {
		Optional<Person> person = personRepository.findById(id);
		if (!person.isPresent())
			throw new EmptyResultDataAccessException(1);
		
		return person.get();
	}
	
	
	
	public Person update(Long id, Person person) {
		Optional<Person> personToUpdate = personRepository.findById(id);
		if(personToUpdate.isPresent()) {
			Person personFound = personToUpdate.get();
			
			Stream<Field> fields = Stream.of(personFound.getClass().getDeclaredFields());
			
			fields.forEach(field -> {
				try {
					field.setAccessible(true);
					if(field.get(person) == null)
						field.set(person, field.get(personFound));
				} catch (IllegalArgumentException | IllegalAccessException e) {e.printStackTrace();}
			});
			
			BeanUtils.copyProperties(person, personFound, "id");
			return personRepository.save(personFound);
		}
		else
			throw new EmptyResultDataAccessException(1);
	}

}
