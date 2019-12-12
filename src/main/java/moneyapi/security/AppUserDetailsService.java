package moneyapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import moneyapi.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<moneyapi.model.User> userOptional = userRepository.findByEmail(email);
		moneyapi.model.User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User and/or password is incorrect"));
		return new User(email, user.getPassword(), getPermissions(user));
	}

	private Collection<? extends GrantedAuthority> getPermissions(moneyapi.model.User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}

}