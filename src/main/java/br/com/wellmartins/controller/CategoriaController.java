package br.com.wellmartins.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wellmartins.entity.Categoria;
import br.com.wellmartins.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@GetMapping("/listar")
	public List<Categoria> listarCategoria(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria entity, HttpServletResponse response) {
			Categoria resposta = categoriaRepository.save(entity);
					// atraves dessa class ServletUriComponentsBuilder
			URI uri = ServletUriComponentsBuilder
					// vou pegar a partir da uri atual e adicionar /{codigo}
					.fromCurrentRequestUri().path("/{codigo}")
					//adicionar o codigo na uri
					.buildAndExpand(resposta.getCodigo())
					.toUri();
			// setando o location no header com essa uri
			 response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(resposta);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo)
				.map(cat -> ResponseEntity.ok(cat))
				.orElse(ResponseEntity.notFound().build());	
	}

}
