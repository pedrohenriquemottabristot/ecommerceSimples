package com.example.ecommerceSimples.services;

import com.example.ecommerceSimples.dtos.ItemPedidoDTO;
import com.example.ecommerceSimples.models.ItemPedido;
import com.example.ecommerceSimples.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional(readOnly = true)
    public ItemPedidoDTO findById(Long id) {
        ItemPedido item = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        return new ItemPedidoDTO(item.getId(), item.getProdutoId(), item.getNomeProduto(), item.getQuantidade(), item.getPreco());
    }

    @Transactional(readOnly = true)
    public List<ItemPedidoDTO> findAll() {
        List<ItemPedido> list = itemPedidoRepository.findAll();
        return list.stream().map(item -> new ItemPedidoDTO(item.getId(), item.getProdutoId(), item.getNomeProduto(), item.getQuantidade(), item.getPreco())).collect(Collectors.toList());
    }

    @Transactional
    public ItemPedidoDTO create(ItemPedidoDTO dto) {
        ItemPedido entity = new ItemPedido(dto);
        entity = itemPedidoRepository.save(entity);
        return new ItemPedidoDTO(entity.getId(), entity.getProdutoId(), entity.getNomeProduto(), entity.getQuantidade(), entity.getPreco());
    }

    @Transactional
    public ItemPedidoDTO update(Long id, ItemPedidoDTO dto) {
        ItemPedido entity = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        entity.updateFromDTO(dto);
        entity = itemPedidoRepository.save(entity);
        return new ItemPedidoDTO(entity.getId(), entity.getProdutoId(), entity.getNomeProduto(), entity.getQuantidade(), entity.getPreco());
    }

    @Transactional
    public void delete(Long id) {
        ItemPedido entity = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));
        itemPedidoRepository.delete(entity);
    }
}

