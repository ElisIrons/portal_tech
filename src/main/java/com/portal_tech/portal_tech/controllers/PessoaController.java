package com.portal_tech.portal_tech.controllers;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(value = "findAllPeople", method = RequestMethod.GET)
    public List<Pessoa> findAll(){
        return this.pessoaService.findAll();
    }


    @RequestMapping(value = "registerperson", method = RequestMethod.POST)
    public ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
        return this.pessoaService.save(pessoaDTO);
    }

    @RequestMapping(value = "/findinf/{id}")
    public ResponseEntity<PessoaDTO>findInfById(@PathVariable long id){
        return this.pessoaService.finfInfById(id);
    }

    @RequestMapping(value = "/updateInf/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PessoaDTO> updateInfById(@PathVariable long id,@RequestBody PessoaDTO pessoaDTO ){
        return this.pessoaService.updateInfById(id,pessoaDTO);
    }

    @RequestMapping(value = "deleteById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String>deleteById(@PathVariable long id){
        return this.pessoaService.deleteById(id);
    }


}
