package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
//@Table(name="chamado") -não precisa, se não tiver, assume q é o nome da classe
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
    private Pessoa id_usuario;

    @ManyToOne(optional = true) //não obrigatório pois só terá técnico após chamado ser atribuído
    @JoinColumn(name = "id_tecnico")
    private Pessoa id_tecnico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_status")
    private Status id_status;

    @ManyToOne(optional = true) //qdo abrir o chamado não terá prioridade, só após o adm ou técnico atribuírem
    @JoinColumn(name = "id_prioridade")
    private Prioridade id_prioridade;

    public Chamado() {
    }

    public Chamado(Long id) {
        this.id = id;
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

    public Pessoa getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Pessoa id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Pessoa getIdTecnico() {
        return id_tecnico;
    }

    public void setIdTecnico(Pessoa id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public Status getIdStatus() {
        return id_status;
    }

    public void setIdStatus(Status id_status) {
        this.id_status = id_status;
    }

    public Prioridade getIdPrioridade() {
        return id_prioridade;
    }

    public void setIdPrioridade(Prioridade id_prioridade) {
        this.id_prioridade = id_prioridade;
    }
}
