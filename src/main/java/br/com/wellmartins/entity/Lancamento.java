package br.com.wellmartins.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wellmartins.entity.enums.TipoLancamento;
import lombok.Data;

@Entity
@Table(name = "LANCAMENTO")
@Data
public class Lancamento {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name ="CODIGO")
	private Long codigo;
	
	@Column(name ="DESCRICAO")
	private String descricao;
	
	@Column(name= "DATA_VENCIMENTO")
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@Column(name = "DATA_PAGAMENTO")
	@JsonFormat(pattern =  "dd/MM/yyyy")
	private LocalDate dataPagamento;
	
	@Column(name ="VALOR")
	private BigDecimal valor;
	
	@Column(name ="OBSERVACAO")
	private String observacao;
	
	@Column(name ="TIPO")
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipo;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_CATEGORIA")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="CODIGO_PESSOA")
	private Pessoa pessoa;
	
}
