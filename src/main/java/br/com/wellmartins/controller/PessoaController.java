package br.com.wellmartins.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wellmartins.entity.Pessoa;
import br.com.wellmartins.event.RecursoCriadoEvent;
import br.com.wellmartins.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		return ResponseEntity.ok(this.pessoaRepository.findAll());
	}
	
	@GetMapping("{codigo}")
	public ResponseEntity<Pessoa> buscarPorCodigoPessoa(@PathVariable Long codigo){
		Optional<Pessoa> respostaCodigo = this.pessoaRepository.findById(codigo);
		return respostaCodigo.map(cod -> ResponseEntity.ok(cod))
				.orElse(ResponseEntity.badRequest().build());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa entidade, HttpServletResponse response){
		Pessoa resposta =  this.pessoaRepository.save(entidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, resposta.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarPessoa(@PathVariable Long codigo) {
		this.pessoaRepository.deleteById(codigo);
	}
}
