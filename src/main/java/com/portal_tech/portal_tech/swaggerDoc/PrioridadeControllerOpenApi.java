package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Tag(name="Prioridade")
public interface PrioridadeControllerOpenApi {

    @Operation(summary = "Método que cria as prioridades padrão.")
    void criarPrioridades(PrioridadeDTO prioridadeDTO);

    @Operation(summary = "Método que retorna todas as prioridades cadastradas na api.")
    public ResponseEntity<List<PrioridadeDTO>> findAll();

    @Operation(summary = "Método que busca uma prioridade por id.")
    public ResponseEntity<PrioridadeDTO> findById(Long id);

    @Operation(summary = "Método para deletar uma prioridade localizada pelo id.")
    public ResponseEntity<String> deleteById(Long id);

    @Operation(summary = "Método para modificar a prioridade localizada pelo id.")
    public ResponseEntity<PrioridadeDTO> updateById(Long id, PrioridadeDTO prioridade);

}
