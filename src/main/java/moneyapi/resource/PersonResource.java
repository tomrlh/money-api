package moneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moneyapi.event.ResourceCreatedEvent;
import moneyapi.model.Person;
import moneyapi.repository.PersonRepository;
import moneyapi.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonResource {
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonService personService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Person> people = personRepository.findAll();
		return !people.isEmpty() ? ResponseEntity.ok(people) : ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> find(@Valid @PathVariable Long id) {
		Person person = personService.findById(id);
		return ResponseEntity.ok(person);
	}
	
	
	
	@PostMapping
	public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response) {
		Person savedPerson = personRepository.save(person);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}
	
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<Person> update(@Valid @PathVariable Long id, @RequestBody Person person) {
		Person updatedPerson = personService.update(id, person);
		return ResponseEntity.ok(updatedPerson);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@Valid @PathVariable Long id) {
		Optional<Person> removedPerson = personRepository.findById(id);
		if(removedPerson.isPresent()) {
			personRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}
