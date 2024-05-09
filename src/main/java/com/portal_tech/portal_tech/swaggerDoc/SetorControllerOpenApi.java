package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Setor")
public interface SetorControllerOpenApi {

    @Operation(summary = "Método para cadastrar um novo setor.")
    List<SetorDTO> save();

    @Operation(summary = "Método que retorna todas os setores cadastrados na api.")
    List<SetorDTO> getAllSetorDTO();

    @Operation(summary = "Método que busca um setor por id.")
    SetorDTO findById(Long id);

    @Operation(summary = "Método para modificar o setor localizado pelo id.")
    SetorDTO updateById(Long id, SetorDTO setorDTO);

    @Operation(summary = "Método para deletar o setor localizado pelo id.")
    SetorDTO deleteById(Long id);

}
