package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.services.PrioridadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name="Prioridade")
public interface PrioridadeControllerOpenApi {

    @Operation(summary = "Método que cria as prioridades padrão.")
    void criarPrioridades();

    @Operation(summary = "Método que retorna todas as prioridades cadastradas na api.")
    public List<PrioridadeDTO> findAll();

    @Operation(summary = "Método que busca uma prioridade por id.")
    public PrioridadeDTO findById(Long id);

    @Operation(summary = "Método para deletar uma prioridade localizada pelo id.")
    public PrioridadeDTO deleteById(Long id);

    @Operation(summary = "Método para modificar a prioridade localizada pelo id.")
    public PrioridadeDTO updateById(Long id, PrioridadeDTO prioridade);

}
