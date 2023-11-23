package com.teste.primeiroexemplo.services;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findTodos(){
        return produtoRepository.findAll();
    }

    public Produto findPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            throw new RuntimeException("Produto não encontrado");

        return produto.get();
    }

    public Produto insert(Produto produto){
        return produtoRepository.save(produto);
    }

    public void delete(Long idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if(produto.isEmpty())
            throw new RuntimeException("Produto não encontrado para ser deletado");

        produtoRepository.delete(produto.get());
    }

    public Produto update(Long idProduto, Produto produto){
        produto.setId(idProduto);
        return produtoRepository.save(produto);
    }

}
