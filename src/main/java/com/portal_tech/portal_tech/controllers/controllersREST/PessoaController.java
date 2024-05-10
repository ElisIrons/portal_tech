package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.PessoaDTO;

import com.portal_tech.portal_tech.services.PessoaService;
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
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @PostMapping("/save")
    public ResponseEntity<PessoaDTO> save( @RequestBody Map<String, Object> pessoaDTO){
        return this.pessoaService.save(pessoaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO>findById(@PathVariable long id){
        return this.pessoaService.findById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaDTO> updateInfById(@PathVariable long id, @RequestBody Map<String, Object>pessoaDTO ){
        return this.pessoaService.updateInfById(id, pessoaDTO);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>deleteById(@PathVariable long id){
        return this.pessoaService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll(){
        return this.pessoaService.findAll();
    }


}
