package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import jakarta.persistence.*;

public record PessoaDTO(String nome, String email, String senha, String telefone, Tipo tipo, Setor setor) {
    public PessoaDTO(String nome, String email, String senha, String telefone, Tipo tipo, Setor setor) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;
        this.setor = setor;
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

}
