package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.serviceBack.ChamadoService;
import com.portal_tech.portal_tech.swaggerDoc.ChamadoControllerOpenApi;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController implements ChamadoControllerOpenApi {

    @Autowired
    private ChamadoService chamadoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "422", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @PostMapping
    public ResponseEntity<ChamadoDTO>save(@RequestBody ChamadoDTO dto){
        return this.chamadoService.save(dto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "422", description = "Erro interno"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar/erro no servidor")
    })
    @GetMapping
    public List<ChamadoDTO>findAll(){
        return  this.chamadoService.findAllChamados();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ID requisitado foi encontrado"),
            @ApiResponse(responseCode = "422", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar/erro no servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar/erro no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "as alterações foram realizadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "faltam dados a serem passados no body/url" ),
            @ApiResponse(responseCode = "500", description = "O ID requisitado não existe/foi encontrado no sistema/erro no servidor")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<ChamadoDTO> updateById(@PathVariable Long id, @RequestBody ChamadoDTO dto){
        return this.chamadoService.updateById(id, dto);
    }
}




