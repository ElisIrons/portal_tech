package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PessoaDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private long tipo;
    private long setor;

    public PessoaDTO() {
    }

    public PessoaDTO(Long id, String nome, String email, String telefone, long tipo, long setor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
        this.setor = setor;
    }

    public PessoaDTO(long id, String nome, String email, String telefone, String senha, long id1, long id2) {
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

    public long getTipo() {
        return tipo;
    }

    public void setTipo(long tipo) {
        this.tipo = tipo;
    }

    public long getSetor() {
        return setor;
    }

    public void setSetor(long setor) {
        this.setor = setor;
    }

    public long tipo() {
        this.tipo = tipo;
        return 0;
    }

}
