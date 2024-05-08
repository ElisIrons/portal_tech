package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping("/cadastrar")
    public List<SetorDTO> save(){
        List<SetorDTO> setor = new ArrayList<>();
        return setor;
    }

    @GetMapping("/mostrar/{id}")
    public SetorDTO findById(@PathVariable long id){
        return this.setorService.findById(id);
    }

    @GetMapping("/mostrar")
    public List<SetorDTO> getAllSetorDTO(){
        return setorService.findAll();
    }

    @PutMapping("/alterar/{id}")
    public SetorDTO updateById(@PathVariable long id, @RequestBody SetorDTO setorDTO){
        return this.setorService.updateById(id,setorDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public SetorDTO deleteById(@PathVariable long id) {
        return this.setorService.deleteById(id);
    }


}
