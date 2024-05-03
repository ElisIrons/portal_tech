package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

@Entity
@Table(name="prioridade")
public class Prioridade {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 30, nullable = false)
    private String nome;

    public Prioridade() {
    }

    public Long getId() {
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
