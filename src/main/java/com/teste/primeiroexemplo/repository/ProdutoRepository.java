package com.teste.primeiroexemplo.repository;

import com.teste.primeiroexemplo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
