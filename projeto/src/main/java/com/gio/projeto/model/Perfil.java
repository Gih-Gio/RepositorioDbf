package com.gio.projeto.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Perfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(name = "nomeUsuario", nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private Date dataNasc;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private Boolean estado;

    @Column(nullable = false)
    private Long nivel;

    @Column(name = "exp", nullable = false)
    private Long experiencia;

    @Column(nullable = false)
    private Long totalCookets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil", orphanRemoval = true)
    private List<Cookie> cookies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "perfil_savejogo", 
      joinColumns = 
        { @JoinColumn(name = "perfil_id", referencedColumnName = "id") },
      inverseJoinColumns = 
        { @JoinColumn(name = "save_id", referencedColumnName = "id") })
    private Save save;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "perfil_cartao", 
      joinColumns = 
        { @JoinColumn(name = "perfil_id") },
      inverseJoinColumns = 
        { @JoinColumn(name = "save_id") })
    private Set<CartaoCredito> cartoes = new HashSet<>();
}