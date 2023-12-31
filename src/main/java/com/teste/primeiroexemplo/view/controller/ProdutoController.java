package com.teste.primeiroexemplo.view.controller;

import com.teste.primeiroexemplo.services.ProdutoService;
import com.teste.primeiroexemplo.shared.ProdutoDTO;
import com.teste.primeiroexemplo.view.model.ProdutoRequest;
import com.teste.primeiroexemplo.view.model.ProdutoResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/obtertodos")
    public ResponseEntity<List<ProdutoResponse>> getTodos(){
        List<ProdutoDTO> produtos = produtoService.findTodos();
        List<ProdutoResponse> produtosResponse = produtos.stream()
                .map(produtoDTO -> new ModelMapper().map(produtoDTO, ProdutoResponse.class))
                .toList();
        return new ResponseEntity<>(produtosResponse, HttpStatus.OK);
    }

    @GetMapping("/obterPorId/{id}")
    public ResponseEntity<ProdutoResponse> getPorId(@PathVariable Long id){
        ProdutoResponse produtoResponse = new ModelMapper().map(produtoService.findPorId(id), ProdutoResponse.class);
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProdutoResponse> adicionarProduto(@RequestBody ProdutoRequest produto){
        ProdutoDTO produtoDTO = new ModelMapper().map(produto, ProdutoDTO.class);
        ProdutoResponse produtoResponse = new ModelMapper().map(produtoService.insert(produtoDTO), ProdutoResponse.class);
        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        produtoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest produto){
        ProdutoDTO produtoDTO = new ModelMapper().map(produto, ProdutoDTO.class);
        ProdutoResponse produtoResponse = new ModelMapper().map(produtoService.update(id, produtoDTO), ProdutoResponse.class);
        return ResponseEntity.ok(produtoResponse);
    }

}
