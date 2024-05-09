package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 30, nullable = false)
    private String nome;

    //@OneToMany(mappedBy = "id_status")
    //private List<Chamado> chamados;

    public Status() {
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
