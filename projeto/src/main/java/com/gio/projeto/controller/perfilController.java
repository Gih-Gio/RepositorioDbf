package com.gio.projeto.controller;


import com.gio.projeto.model.Perfil;
import com.gio.projeto.repository.PerfilRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class perfilController {
    private final Logger log = LoggerFactory.getLogger(perfilController.class);
    
    private final PerfilRepository perfilRepository;

    public perfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> findAllList(){
        log.debug("Função para listar TODOS os perfis solicitada em: CONTROLLER.");
        return perfilRepository.findAll();
    }

    public Optional<Perfil> findOne(Long id) {
        log.debug("Função para listar um perfil específico solicitada em: CONTROLLER; id: ", id);
        return perfilRepository.findById(id);
    }

    public Perfil save(Perfil perfil){
        log.debug("Função para salvar um perfil solicitada em: CONTROLLER.");
        return perfilRepository.save(perfil);
    }

    public void delete(Long id) {
        log.debug("Função para deletar um perfil solicitada em: CONTROLLER; id: ", id);
        perfilRepository.deleteById(id);
    }
}
