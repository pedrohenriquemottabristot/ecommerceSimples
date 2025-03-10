package com.example.ecommerceSimples.services;

import com.example.ecommerceSimples.dtos.UsuarioDTO;
import com.example.ecommerceSimples.models.Usuario;
import com.example.ecommerceSimples.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        List<Usuario> list = usuarioRepository.findAll();
        return list.stream()
                .map(usuario -> new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO create(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Transactional
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Transactional
    public void delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
}
