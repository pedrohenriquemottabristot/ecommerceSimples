package com.example.ecommerceSimples.repositories;

import com.example.ecommerceSimples.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
