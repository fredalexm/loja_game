package com.generation.lojagames.repository;

import com.generation.lojagames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    public List<Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);

    public List<Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);

}
