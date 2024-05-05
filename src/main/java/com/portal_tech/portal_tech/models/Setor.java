package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 100, nullable = false)
    private String nome;

    public Setor() {
    }

    public Setor(long id) {
    }

    public Setor(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
