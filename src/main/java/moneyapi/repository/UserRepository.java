package moneyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import moneyapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
	
}