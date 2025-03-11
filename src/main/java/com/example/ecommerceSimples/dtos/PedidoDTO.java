package com.example.ecommerceSimples.dtos;

import com.example.ecommerceSimples.models.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private String status;
    private UsuarioDTO usuario; // Mudado para UsuarioDTO
    private List<ItemPedidoDTO> itens; // Mudado para List<ItemPedidoDTO>

    public PedidoDTO() {}

    public PedidoDTO(Long id, String status, UsuarioDTO usuario, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.status = status;
        this.usuario = usuario;
        this.itens = itens;
    }
}
