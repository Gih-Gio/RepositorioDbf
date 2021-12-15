package com.gio.projeto.web.api;

import com.gio.projeto.controller.ClasseCookieController;
import com.gio.projeto.model.ClasseCookie;

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
@RequestMapping("/classe")
public class ClasseCookieResource {
    private final Logger log = LoggerFactory.getLogger(ClasseCookieResource.class);

    private final ClasseCookieController classeCookieController;

    public ClasseCookieResource(ClasseCookieController classeCookieController) {
        this.classeCookieController = classeCookieController;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClasseCookie>> getClassesCookie(){
        log.debug("Função para listar TODAS as classes de cookie solicitada por REST em: WEB.");
        List<ClasseCookie> lista = classeCookieController.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/{id}")
    public ResponseEntity<ClasseCookie> getClasseCookie(@PathVariable Long id) {
        log.debug("Função para listar uma classe de cookie específica solicitada por REST em: WEB; id: ", id);
        Optional<ClasseCookie> classeCookie = classeCookieController.findOne(id);
        if(classeCookie.isPresent()) {
            return ResponseEntity.ok().body(classeCookie.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ClasseCookie> createClasseCookie(
            @RequestBody ClasseCookie classeCookie
    ) throws URISyntaxException {
        log.debug("Função para criar uma classe de cookie solicitada por REST em: WEB. Classe: ", classeCookie);
        if (classeCookie.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Não é possível criar uma classe com id definido.");
        }
        ClasseCookie result = classeCookieController.save(classeCookie);
        return ResponseEntity.created(new URI("/api/classes/" + result.getId()))
            .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<ClasseCookie> updateClasseCookie(@RequestBody ClasseCookie classeCookie) throws URISyntaxException {
        log.debug("Função para atualizar uma classe de cookie solicitada por REST em: WEB. Classe: ", classeCookie);
        if (classeCookie.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Classe inválida: id nulo");
        }
        ClasseCookie result = classeCookieController.save(classeCookie);
        return ResponseEntity.ok()
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasseCookie(@PathVariable Long id) {
        log.debug("Função para deletar uma classe de cookie solicitada por REST em: WEB; id: ", id);
        classeCookieController.delete(id);
        return ResponseEntity.noContent().build();
    }
}
