package com.example.ecommerceSimples.repositories;

import com.example.ecommerceSimples.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
