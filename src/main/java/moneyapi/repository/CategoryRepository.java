package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}