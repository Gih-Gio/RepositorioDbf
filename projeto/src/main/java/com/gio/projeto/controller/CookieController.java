package com.gio.projeto.controller;

import com.gio.projeto.model.Cookie;

import com.gio.projeto.repository.CookieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CookieController {
    private final Logger log = LoggerFactory.getLogger(CookieController.class);
    
    private final CookieRepository cookieRepository;

    public CookieController(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    public List<Cookie> findAllList(){
        log.debug("Função para listar TODOS os cookies solicitada em: CONTROLLER.");
        return cookieRepository.findAll();
    }

    public Optional<Cookie> findOne(Long id) {
        log.debug("Função para listar um cookie específico solicitada em: CONTROLLER; id: ", id);
        return cookieRepository.findById(id);
    }

    public Cookie save(Cookie cookie){
        log.debug("Função para salvar um cookie solicitada em: CONTROLLER.");
        return cookieRepository.save(cookie);
    }

    public void delete(Long id) {
        log.debug("Função para deletar um cookie solicitada em: CONTROLLER; id: ", id);
        cookieRepository.deleteById(id);
    }
}
