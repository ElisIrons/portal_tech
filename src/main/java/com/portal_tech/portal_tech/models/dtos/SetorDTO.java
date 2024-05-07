package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Setor;

public class SetorDTO {
    private long id;
    private String nome;

    public SetorDTO(){

    }

public SetorDTO(Setor setor){
this.id = setor.getId();
this.nome = setor.getNome();
}

public static Setor convert(SetorDTO setorDTO){
    Setor setor = new Setor();
    setor.setId(setorDTO.getId());
    setor.setNome(setorDTO.getNome());
    return setor;
}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}