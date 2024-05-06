package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

@Entity
@Table(name="tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String nome;

    public Tipo() {
    }

    public Tipo(Long id) {
    }

    public Tipo(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
