package moneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
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
import org.springframework.web.bind.annotation.PatchMapping;

import moneyapi.event.ResourceCreatedEvent;
import moneyapi.model.Category;
import moneyapi.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<Category> categories = categoryRepository.findAll();
		return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
	}

	
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> find(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.isPresent() ? ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
	}

	
	
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
		Category savedCategory = categoryRepository.save(category);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, savedCategory.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable Long id) {
		Optional<Category> deletedCategory = categoryRepository.findById(id);
		if(deletedCategory.isPresent()) {
			categoryRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}