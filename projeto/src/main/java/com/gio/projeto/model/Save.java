package com.gio.projeto.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SaveJogo")
public class Save {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1000)
    @Lob
    private String configPerfil;

    @Column(nullable = false, length = 1000)
    @Lob 
    private String conquistas;

    @Column(nullable = false, length = 1000)
    @Lob 
    private String upgrades;

    @OneToOne(mappedBy = "save")
    private Perfil perfil;
}