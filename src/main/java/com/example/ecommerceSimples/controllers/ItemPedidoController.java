package com.example.ecommerceSimples.controllers;

import com.example.ecommerceSimples.dtos.ItemPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/itenspedido")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping (value = "/id")
    public ResponseEntity<ItemPedidoDTO> findById (@PathVariable Long id) {
        ItemPedidoDTO dto = itemPedidoService.findById(id);
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> findAll(){
        List <ItemPedidoDTO> dtoList =itemPedidoService.findAll();
        return ResponseEntity.ok(dtoList);
    }
    @PostMapping
    public ResponseEntity <ItemPedidoDTO> create (@RequestBody ItemPedidoDTO dto){
        ItemPedidoDTO createdDto = itemPedidoService.create(dto);
        return ResponseEntity.ok(createdDto);
    }
@PutMapping (value = "/id")
    public ResponseEntity <ItemPedidoDTO> update (@PathVariable Long id, @RequestBody ItemPedidoDTO dto){
        ItemPedidoDTO updatedDto = itemPedidoService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }
    @DeleteMapping (value = "/id")
    public ResponseEntity <Void> delete (@PathVariable Long id){
        itemPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
