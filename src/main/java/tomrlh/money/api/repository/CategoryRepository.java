package tomrlh.money.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomrlh.money.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}