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

import br.com.wellmartins.entity.Lancamento;
import br.com.wellmartins.event.RecursoCriadoEvent;
import br.com.wellmartins.service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> lsitarLancamento(){
		return ResponseEntity.ok(this.lancamentoService.listarLancamentos());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo){
		return this.lancamentoService.buscarPorCodigo(codigo)
				.map(lancamento -> ResponseEntity.ok(lancamento))
				.orElse(ResponseEntity.notFound().build());
				
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvarLancamento(@Valid @RequestBody Lancamento param, HttpServletResponse response){
		Lancamento lancamento = this.lancamentoService.salvarLancamento(param);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamento.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}

}
