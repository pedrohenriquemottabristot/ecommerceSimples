package com.example.ecommerceSimples.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

    private Long id;
    private ProdutoDTO produto; // Alterado para ProdutoDTO
    private int quantidade;
    private Double preco;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long id, ProdutoDTO produto, int quantidade, Double preco) {
        this.id = id;
        this.produto = produto; // Alterado para ProdutoDTO
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
