package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record PessoaDTO( Long id, String nome, String email, String senha, String telefone, long tipo, long setor) {

    public Long id(){
        return this.id;
    }
    public String nome(){
        return this.nome;
    }
    public String email(){
        return this.email;
    }

    public String senha(){
        return this.senha;
    }

    public String telefone(){
        return this.telefone;
    }
    public long tipo(){
        return this.tipo;
    }

    public long setor(){
        return this.setor;
    }
}