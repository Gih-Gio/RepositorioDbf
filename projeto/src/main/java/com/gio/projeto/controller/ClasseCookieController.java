package com.gio.projeto.controller;

import com.gio.projeto.model.ClasseCookie;
import com.gio.projeto.repository.ClasseCookieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseCookieController {
    private final Logger log = LoggerFactory.getLogger(ClasseCookieController.class);
    
    private final ClasseCookieRepository classeCookieRepository;

    public ClasseCookieController(ClasseCookieRepository classeCookieRepository) {
        this.classeCookieRepository = classeCookieRepository;
    }

    public List<ClasseCookie> findAllList(){
        log.debug("Função para listar TODAS as classes de cookie solicitada em: CONTROLLER.");
        return classeCookieRepository.findAll();
    }

    public Optional<ClasseCookie> findOne(Long id) {
        log.debug("Função para listar uma classe de cookie específica solicitada em: CONTROLLER; id: ", id);
        return classeCookieRepository.findById(id);
    }

    public ClasseCookie save(ClasseCookie classeCookie){
        log.debug("Função para salvar uma classe de cookie solicitada em: CONTROLLER.");
        return classeCookieRepository.save(classeCookie);
    }

    public void delete(Long id) {
        log.debug("Função para deletar uma classe de cookie solicitada em: CONTROLLER; id: ", id);
        classeCookieRepository.deleteById(id);
    }
}
