package com.gio.projeto.web.api;

import com.gio.projeto.controller.ClasseCookieController;
import com.gio.projeto.controller.CookieController;
import com.gio.projeto.model.Cookie;

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
@RequestMapping("/cookie")
public class CookieResource {
    private final Logger log = LoggerFactory.getLogger(CookieResource.class);

    private final CookieController cookieController;

    public CookieResource(CookieController cookieController) {
        this.cookieController = cookieController;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cookie>> getCookies(){
        log.debug("Função para listar TODOS os cookies solicitada por REST em: WEB.");
        List<Cookie> lista = cookieController.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Cookie> getCookie(@PathVariable Long id) {
        log.debug("Função para listar um cookie específico solicitada por REST em: WEB; id: ", id);
        Optional<Cookie> cookie = cookieController.findOne(id);
        if(cookie.isPresent()) {
            return ResponseEntity.ok().body(cookie.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Cookie> createCookie(
            @RequestBody Cookie cookie
    ) throws URISyntaxException {
        log.debug("Função para criar um cookie solicitada por REST em: WEB. Cookie: ", cookie);
        if (cookie.getId() != null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Não é possível criar um cookie com id definido.");
        }
        Cookie result = cookieController.save(cookie);
        return ResponseEntity.created(new URI("/api/cookies/" + result.getId()))
            .body(result);
    }

    @PutMapping("/")
    public ResponseEntity<Cookie> updateCookie(@RequestBody Cookie cookie) throws URISyntaxException {
        log.debug("Função para atualizar um cookie solicitada por REST em: WEB. Cookie: ", cookie);
        if (cookie.getId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Cookie inválido: id nulo");
        }
        Cookie result = cookieController.save(cookie);
        return ResponseEntity.ok()
            .body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartao(@PathVariable Long id) {
        log.debug("Função para deletar um cookie solicitada por REST em: WEB; id: ", id);
        cookieController.delete(id);
        return ResponseEntity.noContent().build();
    }
}
