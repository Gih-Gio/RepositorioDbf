package com.gio.projeto.web.api;

import com.gio.projeto.controller.CartaoCreditoController;
import com.gio.projeto.model.CartaoCredito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartao")
public class CartaoCreditoResource {
    private final Logger log = LoggerFactory.getLogger(CartaoCreditoResource.class);

    private final CartaoCreditoController cartaoCreditoController;

    public CartaoCreditoResource(CartaoCreditoController cartaoCreditoController) {
        this.cartaoCreditoController = cartaoCreditoController;
    }

    @GetMapping("/")
    public ResponseEntity<List<CartaoCredito>> getCartoesCredito(){
        log.debug("Função para listar TODOS os cartões de crédito solicitada por REST em: WEB.");
        List<CartaoCredito> lista = cartaoCreditoController.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/{id}")
    public ResponseEntity<CartaoCredito> getCartaoCredito(@PathVariable Long id) {
        log.debug("Função para listar um cartão de crédito específico solicitada por REST em: WEB; id: ", id);
        Optional<CartaoCredito> cartaoCredito = cartaoCreditoController.findOne(id);
        if(cartaoCredito.isPresent()) {
            return ResponseEntity.ok().body(cartaoCredito.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<CartaoCredito> createCartaoCredito(
            @RequestBody CartaoCredito cartaoCredito
    ) throws URISyntaxException {
        log.debug("Função para criar um cartão de crédito solicitada por REST em: WEB. Cartão: ", cartaoCredito);
        if (cartaoCredito.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Não é possível criar um cartão com id definido.");
        }
        CartaoCredito result = cartaoCreditoController.save(cartaoCredito);
        return ResponseEntity.created(new URI("/api/cartoes/" + result.getId()))
            .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<CartaoCredito> updateCartaoCredito(@RequestBody CartaoCredito cartaoCredito) throws URISyntaxException {
        log.debug("Função para atualizar um cartão de crédito solicitada por REST em: WEB. Cartão: ", cartaoCredito);
        if (cartaoCredito.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Cartão inválido: id nulo");
        }
        CartaoCredito result = cartaoCreditoController.save(cartaoCredito);
        return ResponseEntity.ok()
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartaoCredito(@PathVariable Long id) {
        log.debug("Função para deletar um cartão de crédito solicitada por REST em: WEB; id: ", id);
        cartaoCreditoController.delete(id);
        return ResponseEntity.noContent().build();
    }

}
