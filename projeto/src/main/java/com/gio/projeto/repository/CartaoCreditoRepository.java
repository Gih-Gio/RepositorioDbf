package com.gio.projeto.repository;

import com.gio.projeto.model.CartaoCredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long>{
    
}
