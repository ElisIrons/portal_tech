package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Tag(name="Pessoa")
public interface PessoaControllerOpenApi {
    @Operation(summary = "método para salvar dados da pessoa no banco")
    ResponseEntity<PessoaDTO> save(PessoaDTO pessoaDTO);
    @Operation(summary = "método para encontrar a pessoa por ID")
    ResponseEntity<PessoaDTO>findInfById(long id);
    @Operation(summary = "método para realizar modificações de informações pessoais da pessoa")
    ResponseEntity<PessoaDTO>updateInfById( long id, PessoaDTO pessoaDTO );
    @Operation(summary = "método para deletar os dados da pessoa localizado pelo id")
    ResponseEntity<String>deleteById(long id);
}
