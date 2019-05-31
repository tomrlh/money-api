package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Pessoa;

public interface PessoaRepository extends  JpaRepository<Pessoa, Long>{

}
