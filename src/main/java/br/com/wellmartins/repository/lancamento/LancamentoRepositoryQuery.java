package br.com.wellmartins.repository.lancamento;

import java.util.List;

import br.com.wellmartins.entity.Lancamento;
import br.com.wellmartins.entity.filtro.LancamentoFiltro;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar (LancamentoFiltro lacamentoFiltro);
}
