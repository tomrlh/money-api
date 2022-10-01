package tomrlh.money.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tomrlh.money.api.model.Category;
import tomrlh.money.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
