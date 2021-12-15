package com.gio.projeto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class ClasseCookie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private Long vidaBase;

    @Column(nullable = false)
    private Long ataqueBase;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double multiplicadorAtaqueBase;

    @Column(nullable = false)
    private Long poderMagicoBase;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double multiplicadorPoderMagicoBase;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double multiplicadorCPSBase;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classe", orphanRemoval = false)
    private List<Cookie> cookies = new ArrayList<>();
}