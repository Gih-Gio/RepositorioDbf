package com.gio.projeto.web.api;

import com.gio.projeto.controller.perfilController;
import com.gio.projeto.model.Perfil;

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
@RequestMapping("/perfil")
public class PerfilResource {
    private final Logger log = LoggerFactory.getLogger(PerfilResource.class);

    private final perfilController perfilController;

    public PerfilResource(perfilController perfilController) {
        this.perfilController = perfilController;
    }

    @GetMapping("/")
    public ResponseEntity<List<Perfil>> getPerfis(){
        log.debug("Função para listar TODOS os perfis solicitada por REST em: WEB.");
        List<Perfil> lista = perfilController.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getPerfil(@PathVariable Long id) {
        log.debug("Função para listar um perfil específico solicitada por REST em: WEB; id: ", id);
        Optional<Perfil> perfil = perfilController.findOne(id);
        if(perfil.isPresent()) {
            return ResponseEntity.ok().body(perfil.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Perfil> createPerfil(
            @RequestBody Perfil perfil
    ) throws URISyntaxException {
        log.debug("Função para criar um perfil solicitada por REST em: WEB. Perfil: ", perfil);
        if (perfil.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Não é possível criar um perfil com id definido.");
        }
        Perfil result = perfilController.save(perfil);
        return ResponseEntity.created(new URI("/api/perfis/" + result.getId()))
            .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Perfil> updatePerfil(@RequestBody Perfil perfil) throws URISyntaxException {
        log.debug("Função para atualizar um perfil solicitada por REST em: WEB. Perfil: ", perfil);
        if (perfil.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Perfil inválido: id nulo");
        }
        Perfil result = perfilController.save(perfil);
        return ResponseEntity.ok()
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Long id) {
        log.debug("Função para deletar um perfil solicitada por REST em: WEB; id: ", id);
        perfilController.delete(id);
        return ResponseEntity.noContent().build();
    }
}
