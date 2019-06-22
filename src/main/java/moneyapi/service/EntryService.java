package moneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import moneyapi.model.Category;
import moneyapi.model.Entry;
import moneyapi.model.Person;
import moneyapi.repository.EntryRepository;

@Service
public class EntryService {

	@Autowired
	EntryRepository entryRepository;
	@Autowired
	PersonService personService;
	@Autowired
	CategoryService categoryService;
	
	
	
	public Entry findById(Long id) {
		Optional<Entry> person = entryRepository.findById(id);
		if (!person.isPresent())
			throw new EmptyResultDataAccessException(1);
		
		return person.get();
	}
	
	
	
	public void formatEntry(Entry entry) {
		Person person = personService.findById(entry.getPerson().getId());
		Category category = categoryService.findById(entry.getCategory().getId());
		
		entry.setPerson(person);
		entry.setCategory(category);
	}
}
