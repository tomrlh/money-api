package moneyapi.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusResource {

	@GetMapping
	public ResponseEntity<?> status() {
        return ResponseEntity.status(HttpStatus.CREATED).body("HelloWorld!");
	}
}