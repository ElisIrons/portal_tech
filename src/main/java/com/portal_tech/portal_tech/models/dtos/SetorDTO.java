package com.portal_tech.portal_tech.models.dtos;

import jakarta.persistence.Column;

public record SetorDTO(long id, String nome) {

    public long setorId() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }
}
