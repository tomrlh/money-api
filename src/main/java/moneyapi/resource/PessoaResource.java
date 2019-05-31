package moneyapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import moneyapi.event.RecursoCriadoEvent;
import moneyapi.model.Pessoa;
import moneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return !pessoas.isEmpty() ? ResponseEntity.ok(pessoas) : ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscar(@Valid @PathVariable Long codigo) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
	}
	
	
	
	@PostMapping
	public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@Valid @PathVariable Long codigo) {
		Optional<Pessoa> pessoaRemovida = pessoaRepository.findById(codigo);
		if(pessoaRemovida.isPresent()) {
			pessoaRepository.deleteById(codigo);
			return ResponseEntity.noContent().build();
		}
		else
			return ResponseEntity.notFound().build();
	}
}
