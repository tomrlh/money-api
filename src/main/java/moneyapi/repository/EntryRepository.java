package moneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}