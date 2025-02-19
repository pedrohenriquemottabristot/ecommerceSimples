package com.example.ecommerceSimples.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;
    private int quantidadeEstoque;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, Double preco, String descricao, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
