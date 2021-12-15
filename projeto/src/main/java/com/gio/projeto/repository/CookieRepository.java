package com.gio.projeto.repository;

import com.gio.projeto.model.Cookie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends JpaRepository<Cookie, Long>{
    
}
