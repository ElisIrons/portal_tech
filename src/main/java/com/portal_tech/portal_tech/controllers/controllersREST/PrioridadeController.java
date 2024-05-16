package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.services.serviceBack.PrioridadeService;
import com.portal_tech.portal_tech.swaggerDoc.PrioridadeControllerOpenApi;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController implements PrioridadeControllerOpenApi {

    @Autowired
    private PrioridadeService prioridadeService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public void criarPrioridades(PrioridadeDTO prioridadeDTO) {
        this.prioridadeService.CriaPrioridade(prioridadeDTO);

    }

    /*@RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public Prioridade save (@RequestBody Prioridade prioridade) {
        return this.prioridadeService.save(prioridade);
    }*/

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Erro interno"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @RequestMapping(value = "/mostrar", method = RequestMethod.GET)
    public ResponseEntity<List<PrioridadeDTO>> findAll(){
        return this.prioridadeService.findAll();
    }


    @RequestMapping(value = "/mostrar/{id}", method = RequestMethod.GET)
    public ResponseEntity<PrioridadeDTO> findById(@PathVariable Long id) {
        return this.prioridadeService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return this.prioridadeService.deleteById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PrioridadeDTO> updateById(@PathVariable Long id, @RequestBody PrioridadeDTO prioridade) {
        return this.prioridadeService.updateById(id, prioridade);
    }

}