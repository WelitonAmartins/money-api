package br.com.wellmartins.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.wellmartins.entity.Lancamento;
import br.com.wellmartins.entity.Pessoa;
import br.com.wellmartins.repository.LancamentoRepository;
import br.com.wellmartins.repository.PessoaRepository;
import br.com.wellmartins.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Lancamento> listarLancamentos() {
		return this.lancamentoRepository.findAll();
	}
	
	public Optional<Lancamento> buscarPorCodigo(Long codigo) {
		return this.lancamentoRepository.findById(codigo);
	}
	
	public Lancamento salvarLancamento(Lancamento param) {
		Pessoa pessoa = this.pessoaRepository.findById(param.getPessoa().getId())
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		if (pessoa == null || !pessoa.isAtivo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return this.lancamentoRepository.save(param);
	}
	
	public void deletarLancamento(Long id) {
		this.lancamentoRepository.deleteById(id);
	}
}
