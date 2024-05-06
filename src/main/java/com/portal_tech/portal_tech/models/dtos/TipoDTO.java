package com.portal_tech.portal_tech.models.dtos;

public record TipoDTO(long id, String nome) {

    public Long tipoId() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }
}
