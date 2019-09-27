package br.com.wellmartins.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.wellmartins.entity.Pessoa;
import br.com.wellmartins.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {

		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		
		if (!pessoaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(pessoa, pessoaSalva,"codigo"); 
		pessoa.setId(codigo);
		return pessoaRepository.save(pessoa);
		}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		 Pessoa pessoaSalva = this.pessoaRepository.findById(codigo)
			      .orElseThrow(() -> new EmptyResultDataAccessException(1));
		 
		return pessoaSalva;
	}
	
}
