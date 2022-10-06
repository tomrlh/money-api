package tomrlh.money.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tomrlh.money.api.model.Person;
import tomrlh.money.api.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;
    
    @GetMapping
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> find(@PathVariable Long id) {
        Optional<Person> personFound = personRepository.findById(id);
        if(!personFound.isEmpty()) {
            return ResponseEntity.ok(personFound.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> store(@Valid @RequestBody Person category, HttpServletResponse response) {
        Person savedPerson = personRepository.save(category);

        // adds to response header the Location URI of created resource
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(savedPerson.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(savedPerson);
    }
}
