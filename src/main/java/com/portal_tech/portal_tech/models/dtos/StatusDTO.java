package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Status;

public class StatusDTO {
    private Long id;

    private String nome;

    public StatusDTO() {
    }

    public StatusDTO(Long id) {
        this.id = id;
    }

    public StatusDTO(Status status) { //transforma o status em statusDTO
        this.id = status.getId();
        this.nome = status.getNome();
    }

    // transforma o statusDTO em status - método estático:
    public static Status convert(StatusDTO statusDTO) {
        Status status = new Status(); // instanciamos o tipo
        // setamos em Tipo as propriedades q estão em TipoDTO
        status.setId(statusDTO.getId());
        status.setNome(statusDTO.getNome());
        return status; // retornamos o tipo convertido
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
