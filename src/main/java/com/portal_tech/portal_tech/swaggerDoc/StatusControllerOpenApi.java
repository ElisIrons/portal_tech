package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name="Status")
public interface StatusControllerOpenApi {

    @Operation(summary = "Método para cadastrar um novo Status.")
    public ResponseEntity<StatusDTO>save(StatusDTO statusDTO);

    @Operation(summary = "Método que retorna todos os status cadastradas na api." +
            " Se não houver nenhum registro, uma exceção é lançada indicando que não há status cadastrados. ")
    public ResponseEntity<List<StatusDTO>> findAll();

    @Operation(summary = "Método que busca um status por id. Se o status não for encontrado, " +
            "uma exceção é lançada indicando que o status não foi encontrado.  ")
    public ResponseEntity<StatusDTO> findById(Long id);

    @Operation(summary = "Método para deletar um status localizado pelo id.")
    public ResponseEntity<String> deleteById(Long id);

    @Operation(summary = "Método para modificar um status localizado pelo id.")
    public ResponseEntity<StatusDTO> updateById(Long id, StatusDTO status);

}

