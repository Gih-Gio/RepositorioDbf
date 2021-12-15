package com.gio.projeto.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class CartaoCredito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(nullable = false, length = 50)
    private String numeroCartao;

    @Column(nullable = false, length = 255)
    private String nomeTitular;

    @Column(nullable = false)
    private Integer CVV;

    @Column(nullable = false, name = "validade")
    private Date dataValidade;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartoes")
    private Set<Perfil> perfil = new HashSet<>();
}