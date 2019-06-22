package moneyapi.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moneyapi.event.ResourceCreatedEvent;
import moneyapi.exceptionhandler.MoneyApiExceptionHandler.Error;
import moneyapi.model.Entry;
import moneyapi.repository.EntryRepository;
import moneyapi.repository.filter.EntryFilter;
import moneyapi.service.EntryService;
import moneyapi.service.exception.PersonInactiveException;

@RestController
@RequestMapping("/entry")
public class EntryResource {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private EntryRepository entryRepository;
	@Autowired
	EntryService entryService;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	
	@GetMapping
	public List<Entry> search(EntryFilter entryFilter) {
		return entryRepository.filter(entryFilter);
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Entry> find(@PathVariable Long id) {
		Optional<Entry> entry = entryRepository.findById(id);
		return entry.isPresent() ? ResponseEntity.ok(entry.get()) : ResponseEntity.notFound().build();
	}

	
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
		entry = entryService.formatEntry(entry);
		Entry savedEntry = entryService.save(entry);
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
	
	
	
	@ExceptionHandler({ PersonInactiveException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PersonInactiveException ex) {
		String usrMessage = messageSource.getMessage("person.inactive", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Error> erros = Arrays.asList(new Error(usrMessage, devMessage));
		return ResponseEntity.badRequest().body(erros);
	}
}