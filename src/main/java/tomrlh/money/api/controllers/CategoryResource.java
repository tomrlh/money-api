package tomrlh.money.api.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/{id}")
    public Category find(@PathVariable Long id) {
        return categoryRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Category> store(@RequestBody Category category, HttpServletResponse response) {
        Category savedCategory = categoryRepository.save(category);

        // adds to response header the Location URI of created resource
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
            .buildAndExpand(savedCategory.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(savedCategory);
    }
}
