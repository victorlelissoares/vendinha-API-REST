package com.teste.primeiroexemplo.controller;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/obtertodos")
    public List<Produto> getTodos(){
        return produtoService.findTodos();
    }

    @GetMapping("/obterPorId/{id}")
    public Produto getPorId(@PathVariable Long id){
        return produtoService.findPorId(id);
    }

    @PostMapping("/adicionar")
    public Produto adicionarProduto(@RequestBody Produto produto){
        return produtoService.insert(produto);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable Long id){
        produtoService.delete(id);
    }

    @PutMapping("/atualizar/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
        return produtoService.update(id, produto);
    }

}
