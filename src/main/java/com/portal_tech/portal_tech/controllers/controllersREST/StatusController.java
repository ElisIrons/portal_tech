package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.services.serviceBack.StatusService;
import com.portal_tech.portal_tech.swaggerDoc.StatusControllerOpenApi;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController implements StatusControllerOpenApi {

    @Autowired
    private StatusService statusService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @PostMapping("/save")
    public ResponseEntity<StatusDTO> save(@RequestBody StatusDTO statusDTO){
        return this.statusService.save(statusDTO);
    }

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public ResponseEntity<List<StatusDTO>> findAll(){
      return this.statusService.findAll();
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ID requisitado foi encontrado"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id){
     return this.statusService.findById(id);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro no servidor")
    })
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  this.statusService.deleteById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "as alterações foram realizadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "faltam dados a serem passados no body/url" ),
            @ApiResponse(responseCode = "500", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @PutMapping("update/{id}")
    public ResponseEntity<StatusDTO> updateById(@PathVariable Long id, @RequestBody StatusDTO statusDTO){
        return this.statusService.updateById(id,statusDTO);
    }

}




