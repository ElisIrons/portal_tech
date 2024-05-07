package com.portal_tech.portal_tech.controllers;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.services.PessoaService;

import com.portal_tech.portal_tech.swaggerDoc.PessoaControllerOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<PessoaDTO> save(@RequestBody Map<String, Object>requestBodyMap){
        return this.pessoaService.save(requestBodyMap);
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
