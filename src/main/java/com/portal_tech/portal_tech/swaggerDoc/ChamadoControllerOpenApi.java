package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Chamado")
public interface ChamadoControllerOpenApi {


    @Operation(summary = "Método para cadastrar um novo chamado.")
    public ChamadoDTO save(ChamadoDTO dto);

    @Operation(summary = "Método que retorna todos os chamados cadastradas na api.")
    public List<ChamadoDTO> findAll();

    @Operation(summary = "Método que busca um chamado por id.")
    public ChamadoDTO findById(Long id);

    @Operation(summary = "Método para modificar o chamado localizado pelo id.")
    public ChamadoDTO updateById(Long id, ChamadoDTO dto);

    @Operation(summary = "Método para deletar o chamado localizado pelo id.")
    public ChamadoDTO deleteById(Long id);
}
