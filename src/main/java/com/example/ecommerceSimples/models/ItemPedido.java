package com.example.ecommerceSimples.models;

import com.example.ecommerceSimples.dtos.ItemPedidoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private int quantidade;
    private Double preco;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoDTO dto, Pedido pedido, Produto produto) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
    }

    public ItemPedido(Long id, Pedido pedido, Produto produto, int quantidade, Double preco) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Long getProdutoId() {
        return produto != null ? produto.getId() : null;
    }

    public String getNomeProduto() {
        return produto != null ? produto.getNome() : null;
    }

    public void updateFromDTO(ItemPedidoDTO dto, Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        this.produto = produto;
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
    }
}
