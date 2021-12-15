package com.gio.projeto.controller;

import com.gio.projeto.model.Save;
import com.gio.projeto.repository.SaveRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveController {
    private final Logger log = LoggerFactory.getLogger(SaveController.class);
    
    private final SaveRepository saveRepository;

    public SaveController(SaveRepository saveRepository) {
        this.saveRepository = saveRepository;
    }

    public List<Save> findAllList(){
        log.debug("Função para listar TODOS os saves solicitada em: CONTROLLER.");
        return saveRepository.findAll();
    }

    public Optional<Save> findOne(Long id) {
        log.debug("Função para listar um save específico solicitada em: CONTROLLER; id: ", id);
        return saveRepository.findById(id);
    }

    public Save save(Save save){
        log.debug("Função para salvar um save solicitada em: CONTROLLER.");
        return saveRepository.save(save);
    }

    public void delete(Long id) {
        log.debug("Função para deletar um save solicitada em: CONTROLLER; id: ", id);
        saveRepository.deleteById(id);
    }
}
