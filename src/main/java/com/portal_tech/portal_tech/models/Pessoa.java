package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

@Entity
@Table(name="pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 8)
    private String senha;

    @Column(length = 10)
    private String telefone;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;


    //    @ManyToOne(optional = true) //opcional pois só Usuário terá setor
    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "id_setor")
    private Setor setor;


    public Pessoa() {
    }

    public Pessoa(String nome, String email, String senha, String telefone, Tipo tipo, Setor setor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;
        this.setor = setor;
    }

    public Pessoa(Long id) {
        this.id = id;
    }

    public Pessoa(Pessoa pessoa) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public long pegaSoIDTipo(Tipo tipo) {
        return tipo.getId();
    }


}




