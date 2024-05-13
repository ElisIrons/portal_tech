package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.services.SetorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/setor")
public class SetorController { //implements SetorControllerOpenApi {

    @Autowired
    private SetorService setorService;


    @Autowired
    private SetorRepository setorRepository;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @PostMapping("/cadastrar")
    public Setor save(@RequestBody Setor setor){
        return setorRepository.save(setor);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ID requisitado foi encontrado"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @GetMapping("/mostrar/{id}")
    public ResponseEntity<SetorDTO> findById(@PathVariable Long id)
    {
        return this.setorService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Erro interno")
    })
    @GetMapping("/mostrar")
    public ResponseEntity<List<SetorDTO>> getAllSetorDTO(){
        return setorService.findAll();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "as alterações foram realizadas com sucesso"),
            @ApiResponse(responseCode = "204", description = "faltam dados a serem passados no body/url" ),
            @ApiResponse(responseCode = "500", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<SetorDTO> updateById(@PathVariable Long id, @RequestBody SetorDTO setorDTO){
        return this.setorService.updateById(id,setorDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return this.setorService.deleteById(id);
    }


}
