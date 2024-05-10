package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

@Entity
@Table(name="prioridade")
public class Prioridade {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 30, nullable = false)
    private String nome;


    // Construtor padrão
    public Prioridade() {

    }

    public Prioridade(Long id) {
        this.id = id;
    }

    // Construtor com argumento
    public Prioridade(String nome) {
        this.nome = nome;
    }


    // Getters e setters
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

    public void setIdPrioridade(Long id) {
        this.id = id;
    }
}
