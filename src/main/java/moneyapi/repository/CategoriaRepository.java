package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}