package com.gio.projeto.repository;

import com.gio.projeto.model.ClasseCookie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseCookieRepository extends JpaRepository<ClasseCookie, Long>{
    
}
