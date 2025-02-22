package com.example.ecommerceSimples.dtos;

import com.example.ecommerceSimples.models.ItemPedido;
import com.example.ecommerceSimples.models.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private String status;
    private Usuario usuario;
    private List<ItemPedido> itens;


    public PedidoDTO() {}


    public PedidoDTO(Long id, String status, Usuario usuario, List<ItemPedido> itens) {
        this.id = id;
        this.status = status;
        this.usuario = usuario;
        this.itens = itens;
    }
}
