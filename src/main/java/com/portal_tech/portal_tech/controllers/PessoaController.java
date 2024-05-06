package com.portal_tech.portal_tech.controllers;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.services.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Operation(description = "Salva os dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "os dados foram salvos com sucesso"),
            @ApiResponse(responseCode = "400", description = "Faltam dados obrigatórios a serem passados/não foi possível salvar")
    })
    @PostMapping("/save")
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        return this.pessoaService.save(pessoaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO>findInfById(@PathVariable long id){
        return this.pessoaService.finfInfById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaDTO> updateInfById(@PathVariable long id,@RequestBody PessoaDTO pessoaDTO ){
        return this.pessoaService.updateInfById(id,pessoaDTO);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>deleteById(@PathVariable long id){
        return this.pessoaService.deleteById(id);
    }

//    Este método ainda precisa de uns ajustes, por isso esta comentado
//    @GetMapping
//    public List<Pessoa> findAll(){
//        return this.pessoaService.findAll();
//    }


}
