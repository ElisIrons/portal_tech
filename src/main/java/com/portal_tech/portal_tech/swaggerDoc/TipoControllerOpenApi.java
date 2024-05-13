package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name="Tipo")
public interface TipoControllerOpenApi {

    @Operation(summary = "Método para salvar os tipos que a pessoa pode ser classificada(técnico, administrador, usuário) no banco de dados")
    public ResponseEntity<TipoDTO> save(TipoDTO tipoDTO);
    @Operation(summary = "método que retorna todos tipos cadastrados na api")
    public ResponseEntity<List<TipoDTO>> findAll();

    @Operation(summary = "Método que encontra um tipo por id")
    public ResponseEntity<TipoDTO> findById(Long id);

    @Operation(summary = "método para deletar um tipo localizado pelo id dele")
    public ResponseEntity<String> deleteById(Long id);

    @Operation(summary = "método para modificar o tipo localizado pelo id")
    public ResponseEntity<TipoDTO> updateById(Long id, TipoDTO tipodto);
}
