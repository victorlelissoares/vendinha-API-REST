package com.teste.primeiroexemplo.services;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import com.teste.primeiroexemplo.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> findTodos(){
        List<Produto> produtos =  produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public ProdutoDTO findPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            throw new ResourceNotFoundException("Produto não encontrado");

        return new ModelMapper().map(produto.get(), ProdutoDTO.class);
    }

    public ProdutoDTO insert(ProdutoDTO produtoDto){
        produtoDto.setId(null);
        Produto produtoNovo = new ModelMapper().map(produtoDto, Produto.class);
        produtoNovo = produtoRepository.save(produtoNovo);
        produtoDto.setId(produtoNovo.getId());
        return produtoDto;
    }

    public void delete(Long idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto);

        if(produto.isEmpty())
            throw new ResourceNotFoundException("Produto não encontrado para ser deletado");

        produtoRepository.delete(produto.get());
    }

    public ProdutoDTO update(Long idProduto, ProdutoDTO produtoDto){

        if(!produtoRepository.existsById(idProduto))
            throw new ResourceNotFoundException("Produto não encontrado para ser atualizado");

        produtoDto.setId(idProduto);
        Produto produtoNovo = new ModelMapper().map(produtoDto, Produto.class);
        produtoRepository.save(produtoNovo);
        return produtoDto;
    }

}
