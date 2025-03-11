package com.example.ecommerceSimples.services;

import com.example.ecommerceSimples.dtos.ItemPedidoDTO;
import com.example.ecommerceSimples.dtos.ProdutoDTO;
import com.example.ecommerceSimples.models.ItemPedido;
import com.example.ecommerceSimples.models.Produto;
import com.example.ecommerceSimples.repositories.ItemPedidoRepository;
import com.example.ecommerceSimples.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public ItemPedidoDTO findById(Long id) {
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));

        Produto produto = produtoRepository.findById(item.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());

        return new ItemPedidoDTO(item.getId(), produtoDTO, item.getQuantidade(), item.getPreco());
    }

    @Transactional(readOnly = true)
    public List<ItemPedidoDTO> findAll() {
        List<ItemPedido> list = itemPedidoRepository.findAll();
        return list.stream().map(item -> {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
            return new ItemPedidoDTO(item.getId(), produtoDTO, item.getQuantidade(), item.getPreco());
        }).collect(Collectors.toList());
    }

    @Transactional
    public ItemPedidoDTO create(ItemPedidoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemPedido entity = new ItemPedido();
        entity.setProduto(produto);
        entity.setQuantidade(dto.getQuantidade());
        entity.setPreco(dto.getPreco());

        entity = itemPedidoRepository.save(entity);

        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
        return new ItemPedidoDTO(entity.getId(), produtoDTO, entity.getQuantidade(), entity.getPreco());
    }

    @Transactional
    public ItemPedidoDTO update(Long id, ItemPedidoDTO dto) {
        ItemPedido entity = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));

        Produto produto = produtoRepository.findById(dto.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        entity.setProduto(produto);
        entity.setQuantidade(dto.getQuantidade());
        entity.setPreco(dto.getPreco());

        entity = itemPedidoRepository.save(entity);

        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getDescricao(), produto.getQuantidadeEstoque());
        return new ItemPedidoDTO(entity.getId(), produtoDTO, entity.getQuantidade(), entity.getPreco());
    }

    @Transactional
    public void delete(Long id) {
        ItemPedido entity = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        itemPedidoRepository.delete(entity);
    }
}
