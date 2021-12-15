package com.gio.projeto.web.api;

import com.gio.projeto.controller.SaveController;
import com.gio.projeto.model.Save;

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
@RequestMapping("/save")
public class SaveResource {
    private final Logger log = LoggerFactory.getLogger(SaveResource.class);

    private final SaveController saveController;

    public SaveResource(SaveController saveController) {
        this.saveController = saveController;
    }

    @GetMapping("/")
    public ResponseEntity<List<Save>> getSaves(){
        log.debug("Função para listar TODOS os saves solicitada por REST em: WEB.");
        List<Save> lista = saveController.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Save> getSave(@PathVariable Long id) {
        log.debug("Função para listar um save específico solicitada por REST em: WEB; id: ", id);
        Optional<Save> save = saveController.findOne(id);
        if(save.isPresent()) {
            return ResponseEntity.ok().body(save.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Save> createSave(
            @RequestBody Save save
    ) throws URISyntaxException {
        log.debug("Função para criar um save solicitada por REST em: WEB. Perfil: ", save);
        if (save.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Não é possível criar um save com id definido.");
        }
        Save result = saveController.save(save);
        return ResponseEntity.created(new URI("/api/saves/" + result.getId()))
            .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Save> updateSave(@RequestBody Save save) throws URISyntaxException {
        log.debug("Função para atualizar um save solicitada por REST em: WEB. Save: ", save);
        if (save.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Save inválido: id nulo");
        }
        Save result = saveController.save(save);
        return ResponseEntity.ok()
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSave(@PathVariable Long id) {
        log.debug("Função para deletar um save solicitada por REST em: WEB; id: ", id);
        saveController.delete(id);
        return ResponseEntity.noContent().build();
    }
}
