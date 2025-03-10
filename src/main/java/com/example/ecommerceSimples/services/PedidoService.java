package com.example.ecommerceSimples.services;


import com.example.ecommerceSimples.dtos.PedidoDTO;
import com.example.ecommerceSimples.models.Pedido;
import com.example.ecommerceSimples.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoDTO(pedido.getId(), pedido.getStatus(), pedido.getUsuario(), pedido.getItens());
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        List<Pedido> list = pedidoRepository.findAll();
        return list.stream()
                .map(pedido -> new PedidoDTO(pedido.getId(), pedido.getStatus(), pedido.getUsuario(), pedido.getItens()))
                .collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setStatus(dto.getStatus());
        pedido.setUsuario(dto.getUsuario());
        pedido.setItens(dto.getItens());
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido.getId(), pedido.getStatus(), pedido.getUsuario(), pedido.getItens());
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(dto.getStatus());
        pedido.setUsuario(dto.getUsuario());
        pedido.setItens(dto.getItens());
        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido.getId(), pedido.getStatus(), pedido.getUsuario(), pedido.getItens());
    }

    @Transactional
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }
}



