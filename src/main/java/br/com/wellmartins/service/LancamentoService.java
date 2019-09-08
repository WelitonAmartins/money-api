package br.com.wellmartins.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wellmartins.entity.Lancamento;
import br.com.wellmartins.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public List<Lancamento> listarLancamentos() {
		return this.lancamentoRepository.findAll();
	}
	
	public Optional<Lancamento> buscarPorCodigo(Long codigo) {
		return this.lancamentoRepository.findById(codigo);
	}
	
	public Lancamento salvarLancamento(Lancamento param) {
		return this.lancamentoRepository.save(param);
	}
}
