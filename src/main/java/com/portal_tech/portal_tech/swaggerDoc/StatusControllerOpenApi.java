package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Status")
public interface StatusControllerOpenApi {

    @Operation(summary = "Método para cadastrar um novo Status.")
    public Status save(Status dto);

    @Operation(summary = "Método que retorna todos os status cadastradas na api." +
            " Se não houver nenhum registro, uma exceção é lançada indicando que não há status cadastrados. ")
    public List<Status> findAll();

    @Operation(summary = "Método que busca um status por id. Se o status não for encontrado, " +
            "uma exceção é lançada indicando que o status não foi encontrado.  ")
    public Status findById(Long id);

    @Operation(summary = "Método para deletar um status localizado pelo id.")
    public Status deleteById(Long id);

    @Operation(summary = "Método para modificar um status localizado pelo id.")
    public Status updateById(Long id, Status status);

}

