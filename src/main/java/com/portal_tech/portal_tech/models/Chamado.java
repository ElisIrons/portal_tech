package com.portal_tech.portal_tech.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.Timestamp;
import java.time.LocalDate;

@Entity
//@Table(name="chamado") -não precisa, se não tiver, assume q é o nome da classe
public class Chamado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String descricao;

    @Column (nullable = false)
    private LocalDate dt_abertura;

    @Column
    private LocalDate dt_inicio;

    @Column
    private LocalDate dt_fim;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Pessoa id_usuario;

    @ManyToOne(optional = true, fetch = FetchType.EAGER) //não obrigatório pois só terá técnico após chamado ser atribuído
    @JoinColumn(name = "id_tecnico", referencedColumnName = "id", nullable = true, columnDefinition = "BIGINT DEFAULT 1")
    private Pessoa id_tecnico;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = true, columnDefinition = "BIGINT DEFAULT 1") //insertable = false, updatable = false,
    private Status id_status;

    /*@Column(name = "id_status")
    private Long id_status;*/

    @ManyToOne(optional = true) //qdo abrir o chamado não terá prioridade, só após o adm ou técnico atribuírem
    @JoinColumn(name = "id_prioridade", columnDefinition = "BIGINT DEFAULT 1")
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

    public LocalDate getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(LocalDate dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public LocalDate getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(LocalDate dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public LocalDate getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(LocalDate dt_fim) {
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
