package com.gio.projeto.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Cookie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false)
    private Long nivelAfeto;

    @Column(nullable = false)
    private Long totalVida;

    @Column(nullable = false)
    private Long totalCliquesRecebidos;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double multiplicadorAtaque;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double multiplicadorPoderMagico;

    @Column(name = "multiplicadorCPS", nullable = false, precision = 10, scale = 2)
    private Double multiplicadorCookiesPorSegundo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ClasseCookie_id", foreignKey = @ForeignKey(name = "Cookie_ClasseCookie_FK"))
    private ClasseCookie classe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Perfil_id", foreignKey = @ForeignKey(name = "Cookie_Perfil_FK"))
    private Perfil perfil;
}