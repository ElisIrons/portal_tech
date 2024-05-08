package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.Tipo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name="Tipo")
public interface TipoControllerOpenApi {

    @Operation(summary = "Método para salvar os tipos que a pessoa pode ser classificada(técnico, administrador, usuário) no banco de dados")
    public Tipo save(Tipo tipo);
    @Operation(summary = "método que retorna todos tipos cadastrados na api")
    public List<Tipo> findAll();

    @Operation(summary = "Método que encontra um tipo por id")
    public Tipo findById(Long id);

    @Operation(summary = "método para deletar um tipo localizado pelo id dele")
    public Tipo deleteById(Long id);

    @Operation(summary = "método para modificar o tipo localizado pelo id")
    public Tipo updateById(Long id, Tipo tipo);
}
