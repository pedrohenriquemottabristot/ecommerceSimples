package com.example.ecommerceSimples.services;

import com.example.ecommerceSimples.dtos.PedidoDTO;
import com.example.ecommerceSimples.dtos.ItemPedidoDTO;
import com.example.ecommerceSimples.dtos.UsuarioDTO;
import com.example.ecommerceSimples.dtos.ProdutoDTO;
import com.example.ecommerceSimples.models.Pedido;
import com.example.ecommerceSimples.models.ItemPedido;
import com.example.ecommerceSimples.models.Usuario;
import com.example.ecommerceSimples.models.Produto;
import com.example.ecommerceSimples.repositories.PedidoRepository;
import com.example.ecommerceSimples.repositories.ItemPedidoRepository;
import com.example.ecommerceSimples.repositories.UsuarioRepository;
import com.example.ecommerceSimples.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Usuario usuario = usuarioRepository.findById(pedido.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                .map(item -> new ItemPedidoDTO(item.getId(), new ProdutoDTO(item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getProduto().getDescricao(), item.getProduto().getQuantidadeEstoque()), item.getQuantidade(), item.getPreco()))
                .collect(Collectors.toList());

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());

        return new PedidoDTO(pedido.getId(), pedido.getStatus(), usuarioDTO, itensDTO);
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        List<Pedido> list = pedidoRepository.findAll();
        return list.stream().map(pedido -> {
            Usuario usuario = usuarioRepository.findById(pedido.getUsuario().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            List<ItemPedidoDTO> itensDTO = pedido.getItens().stream()
                    .map(item -> new ItemPedidoDTO(item.getId(), new ProdutoDTO(item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getProduto().getDescricao(), item.getProduto().getQuantidadeEstoque()), item.getQuantidade(), item.getPreco()))
                    .collect(Collectors.toList());

            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
            return new PedidoDTO(pedido.getId(), pedido.getStatus(), usuarioDTO, itensDTO);
        }).collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setStatus(dto.getStatus());
        pedido.setUsuario(usuario);

        Pedido finalPedido = pedido;
        List<ItemPedido> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPreco(itemDTO.getPreco());
            item.setPedido(finalPedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        pedido = pedidoRepository.save(pedido);

        List<ItemPedidoDTO> itensDTO = itens.stream().map(item -> new ItemPedidoDTO(item.getId(), new ProdutoDTO(item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getProduto().getDescricao(), item.getProduto().getQuantidadeEstoque()), item.getQuantidade(), item.getPreco()))
                .collect(Collectors.toList());

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());

        return new PedidoDTO(pedido.getId(), pedido.getStatus(), usuarioDTO, itensDTO);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        pedido.setStatus(dto.getStatus());
        pedido.setUsuario(usuario);

        Pedido finalPedido = pedido;
        List<ItemPedido> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPreco(itemDTO.getPreco());
            item.setPedido(finalPedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        pedido = pedidoRepository.save(pedido);

        List<ItemPedidoDTO> itensDTO = itens.stream().map(item -> new ItemPedidoDTO(item.getId(), new ProdutoDTO(item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getProduto().getDescricao(), item.getProduto().getQuantidadeEstoque()), item.getQuantidade(), item.getPreco()))
                .collect(Collectors.toList());

        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());

        return new PedidoDTO(pedido.getId(), pedido.getStatus(), usuarioDTO, itensDTO);
    }

    @Transactional
    public void delete(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }
}
