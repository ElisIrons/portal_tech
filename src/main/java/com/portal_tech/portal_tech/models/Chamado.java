package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name="chamado")
public class Chamado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String descricao;

    @Column (nullable = false)
    private Timestamp dt_abertura;

    @Column
    private Timestamp dt_inicio;

    @Column
    private Timestamp dt_fim;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Pessoa Usuario;

    @ManyToOne(optional = true) //não obrigatório pois só terá técnico após chamado ser atribuído
    @JoinColumn(name = "id_tecnico")
    private Pessoa Tecnico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToOne(optional = true) //qdo abrir o chamado não terá prioridade, só após o adm ou técnico atribuírem
    @JoinColumn(name = "id_prioridade")
    private Prioridade prioridade;

    public Chamado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(Timestamp dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public Timestamp getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Timestamp dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Timestamp getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Timestamp dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Pessoa getUsuario() {
        return Usuario;
    }

    public void setUsuario(Pessoa usuario) {
        Usuario = usuario;
    }

    public Pessoa getTecnico() {
        return Tecnico;
    }

    public void setTecnico(Pessoa tecnico) {
        Tecnico = tecnico;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}
