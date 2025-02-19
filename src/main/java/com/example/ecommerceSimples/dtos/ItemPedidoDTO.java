package com.example.ecommerceSimples.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {

    private Long id;
    private Long produtoId;
    private String nomeProduto;
    private int quantidade;
    private Double preco;


    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long id, Long produtoId, String nomeProduto, int quantidade, Double preco) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
