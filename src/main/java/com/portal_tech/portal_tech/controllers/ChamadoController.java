package com.portal_tech.portal_tech.controllers;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @PostMapping
    public ChamadoDTO save(@RequestBody ChamadoDTO dto){
        return this.chamadoService.save(dto);
    }

    @GetMapping
    public List<ChamadoDTO> findAll(){
        return this.chamadoService.findAll();
    }

    @GetMapping("/{id}")
    public ChamadoDTO findbyId(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ChamadoDTO deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public ChamadoDTO updateById(@PathVariable Long id, @RequestBody ChamadoDTO dto){
        return this.chamadoService.UpdateById(id, dto);
    }
}

