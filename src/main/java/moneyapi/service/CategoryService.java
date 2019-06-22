package moneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import moneyapi.model.Category;
import moneyapi.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (!category.isPresent())
			throw new EmptyResultDataAccessException(1);
		
		return category.get();
	}
}
