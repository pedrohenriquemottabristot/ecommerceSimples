package com.example.ecommerceSimples.services;

import com.example.ecommerceSimples.dtos.ProdutoDTO;
import com.example.ecommerceSimples.models.Produto;
import com.example.ecommerceSimples.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        List<Produto> list = produtoRepository.findAll();
        return list.stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque()))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
    }

    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }
}

