package br.com.wellmartins.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wellmartins.entity.Categoria;
import br.com.wellmartins.event.RecursoCriadoEvent;
import br.com.wellmartins.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/listar")
	public List<Categoria> listarCategoria(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria entity, HttpServletResponse response) {
			Categoria resposta = categoriaRepository.save(entity);
			publisher.publishEvent(new RecursoCriadoEvent(this, response, resposta.getCodigo()));
			
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo)
				.map(cat -> ResponseEntity.ok(cat))
				.orElse(ResponseEntity.notFound().build());	
	}
}
