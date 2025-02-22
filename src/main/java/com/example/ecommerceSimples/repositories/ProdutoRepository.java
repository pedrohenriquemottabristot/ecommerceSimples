package com.example.ecommerceSimples.repositories;

import com.example.ecommerceSimples.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
