package com.gio.projeto.controller;

import com.gio.projeto.model.CartaoCredito;
import com.gio.projeto.repository.CartaoCreditoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartaoCreditoController {
    private final Logger log = LoggerFactory.getLogger(CartaoCreditoController.class);

    private final CartaoCreditoRepository cartaoRepository;

    public CartaoCreditoController(CartaoCreditoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    public List<CartaoCredito> findAllList(){
        log.debug("Função para listar TODOS os cartões de crédito solicitada em: CONTROLLER.");
        return cartaoRepository.findAll();
    }

    public Optional<CartaoCredito> findOne(Long id) {
        log.debug("Função para listar um cartão de crédito específico solicitada em: CONTROLLER; id: ", id);
        return cartaoRepository.findById(id);
    }

    public CartaoCredito save(CartaoCredito cartao){
        log.debug("Função para salvar um cartão de crédito solicitada em: CONTROLLER.");
        return cartaoRepository.save(cartao);
    }

    public void delete(Long id) {
        log.debug("Função para deletar um cartão de crédito solicitada em: CONTROLLER; id: ", id);
        cartaoRepository.deleteById(id);
    }
}