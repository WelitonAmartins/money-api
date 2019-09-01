package br.com.wellmartins.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name= "CATEGORIA")
@Data
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="COD_CATEGORIA")
	private Long codigo;
	
	@NotNull
	@Size(min=3, max =20)
	@Column(name="NOME")
	private String nome;
}
