package com.portal_tech.portal_tech.swaggerDoc;

import org.springframework.ui.Model;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Usuário")
public interface UsuarioControllerOpenApi {

    @Operation(summary = "Método para Cria um novo chamado")
    public String criarNovoChamado(ChamadoDTO novoChamado);

    @Operation(summary = "Método para visualizar todos os chamados")
    public String visualizarChamados(Model model);

    @Operation(summary = "Método para exclui um chamado por ID")
    public String excluirChamado(Long id);

    @Operation(summary = "Método para acessar a tela de novo chamado")
    public String novaTelaChamado();

    @Operation(summary = "Método para Exibir a tela do usuário")
    public String exibirTelaUsuario();

}

