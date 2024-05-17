package com.portal_tech.portal_tech.controllers.controllersREST;


import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.services.serviceBack.TipoService;
import com.portal_tech.portal_tech.swaggerDoc.TipoControllerOpenApi;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tipo")
public class TipoContoller implements TipoControllerOpenApi {

    @Autowired
    private TipoService tipoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @PostMapping("/save")
    public ResponseEntity<TipoDTO> save(@RequestBody TipoDTO tipoDTO){

        return this.tipoService.save(tipoDTO);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Erro interno"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @GetMapping
    public ResponseEntity<List<TipoDTO>> findAll(){
            return this.tipoService.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ID requisitado foi encontrado"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<TipoDTO> findById(@PathVariable Long id){
       return this.tipoService.findById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return this.tipoService.deleteById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "as alterações foram realizadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "faltam dados a serem passados no body/url" ),
            @ApiResponse(responseCode = "500", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoDTO> updateById(@PathVariable Long id, @RequestBody TipoDTO tipoDTO){
        return this.tipoService.updateById(id, tipoDTO);
    }


}
