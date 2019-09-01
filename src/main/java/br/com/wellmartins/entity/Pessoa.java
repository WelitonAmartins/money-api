package br.com.wellmartins.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name= "PESSOA")
@Data
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COD_PESSOA")
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 50)
	@Column(name="NOME")
	private String nome;
	
	@Embedded
	private Endereco Endereco;
	
	@NotNull
	@Column(name= "ATIVO")
	private boolean ativo;
}
