package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Entry;
import moneyapi.repository.query.EntryRepositoryQuery;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {
	
}