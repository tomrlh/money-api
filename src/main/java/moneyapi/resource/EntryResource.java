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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moneyapi.event.ResourceCreatedEvent;
import moneyapi.model.Entry;
import moneyapi.repository.EntryRepository;
import moneyapi.service.EntryService;

@RestController
@RequestMapping("/entry")
public class EntryResource {

	@Autowired
	private EntryRepository entryRepository;
	@Autowired
	EntryService entryService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Entry> entries = entryRepository.findAll();
		return !entries.isEmpty() ? ResponseEntity.ok(entries) : ResponseEntity.noContent().build();
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> find(@PathVariable Long id) {
		Optional<Entry> entry = entryRepository.findById(id);
		return entry.isPresent() ? ResponseEntity.ok(entry.get()) : ResponseEntity.notFound().build();
	}

	
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		entryService.formatEntry(entry);
		Entry savedEntry = entryRepository.save(entry);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedEntry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable Long id) {
		Optional<Entry> deletedEntry = entryRepository.findById(id);
		if(deletedEntry.isPresent()) {
			entryRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}