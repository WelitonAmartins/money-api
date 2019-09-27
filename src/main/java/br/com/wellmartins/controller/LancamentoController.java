package br.com.wellmartins.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wellmartins.entity.Lancamento;
import br.com.wellmartins.entity.filtro.LancamentoFiltro;
import br.com.wellmartins.event.RecursoCriadoEvent;
import br.com.wellmartins.exception.MoneyExceptionHandler.Erro;
import br.com.wellmartins.repository.LancamentoRepository;
import br.com.wellmartins.service.LancamentoService;
import br.com.wellmartins.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoRepository repo;
	
	@Autowired
	MessageSource messageSource; 
	
//	@GetMapping
//	public ResponseEntity<List<Lancamento>> lsitarLancamento(){
//		return ResponseEntity.ok(this.lancamentoService.listarLancamentos());
//	}
	
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
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@GetMapping
	public List<Lancamento> pesquisar(LancamentoFiltro filtro){
		return this.repo.filtrar(filtro);
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{codigo}")
	public void deletarLancamento(@PathVariable Long id) {
		this.lancamentoService.deletarLancamento(id);
	}
	


}
