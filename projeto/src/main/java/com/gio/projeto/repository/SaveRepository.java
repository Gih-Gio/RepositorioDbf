package com.gio.projeto.repository;

import com.gio.projeto.model.Save;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveRepository extends JpaRepository<Save, Long>{
    
}