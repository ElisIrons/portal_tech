package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Tipo;

public class TipoDTO {
    private Long id;

    private String nome;

    public TipoDTO() {
    }

    public TipoDTO(Long id) {
        this.id = id;
    }

    public TipoDTO(Tipo tipo) { //transforma o tipo em tipoDTO
        this.id = tipo.getId();
        this.nome = tipo.getNome();
    }

    // transforma o tipoDTO em tipo - método estático:
    public static Tipo convert(TipoDTO tipoDTO) {
        Tipo tipo = new Tipo(); // instanciamos o tipo
        // setamos em Tipo as propriedades q estão em TipoDTO
        tipo.setId(tipoDTO.getId());
        tipo.setNome(tipoDTO.getNome());
        return tipo; // retornamos o tipo convertido
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

    }
}