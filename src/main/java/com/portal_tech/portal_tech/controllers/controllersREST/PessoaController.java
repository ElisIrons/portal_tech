package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.services.serviceBack.PessoaService;
import java.util.Map;

import com.portal_tech.portal_tech.swaggerDoc.PessoaControllerOpenApi;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class PessoaController implements PessoaControllerOpenApi {

    @Autowired
    private PessoaService pessoaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "409", description = "A pessoa já foi cadastrada no sistema com este email"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @PostMapping("/save")
    public ResponseEntity<PessoaDTO> save(@RequestBody Map<String, Object> pessoaDTO) {
        return this.pessoaService.register(pessoaDTO);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O ID requisitado foi encontrado"),
            @ApiResponse(responseCode = "422", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable long id) {
        return this.pessoaService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "as alterações foram realizadas com sucesso"),
            @ApiResponse(responseCode = "422", description = "faltam dados a serem passados no body/url"),
            @ApiResponse(responseCode = "500", description = "O ID requisitado não existe/foi encontrado no sistema")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaDTO> updateInfById(@PathVariable long id, @RequestBody Map<String, Object> pessoaDTO) {
        return this.pessoaService.updateInfById(id, pessoaDTO);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O elemento foi deletado com sucesso"),
            @ApiResponse(responseCode = "422", description = "O ID requisitado não existe/foi encontrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        return this.pessoaService.deleteById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return this.pessoaService.findAll();
    }


}