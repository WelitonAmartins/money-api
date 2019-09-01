package br.com.wellmartins.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wellmartins.entity.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{

}
