package com.portal_tech.portal_tech.swaggerDoc;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;


@Tag(name="Técnico")
public interface TecnicoControllerOpenApi {

    @Operation(summary = "Página inicial do técnico")
    public String indexUsuario(Model model);

    @Operation(summary = "Retorna todos os chamados associados ao técnico")
    public String findAllChamados(Model model);

    @Operation(summary = "Retorna os detalhes de um chamado específico")
    public String findById(Long id, Model model);

    @Operation(summary = "Atualiza um chamado pelo ID do usuário")
    public String updateById(Long id, ChamadoDTO chamadoDTO);
}
