package com.portal_tech.portal_tech.swaggerDoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;

@Tag(name="Administrador")
public interface AdministradorControllerOpenApi {

    @Operation(summary = "Acesso ao painel de administração")
    public String adminPainel(Model model);
}
