package com.portal_tech.portal_tech.controllers;

import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.swaggerDoc.StatusControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController

public class StatusController implements StatusControllerOpenApi {

    @Autowired
    private StatusRepository statusRepository;

    @RequestMapping(value="cadstatus", method = RequestMethod.POST)
    public Status save(@RequestBody Status status){
        return statusRepository.save(status);
    }

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public List<Status> findAll(){
        List<Status> status = statusRepository.findAll();
        if (status.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há status cadastrados!");
        }
        else {
            return status;
        }
    }

    @RequestMapping(value="status/{id}", method = RequestMethod.GET)
    public Status findById(@PathVariable Long id){
        Optional<Status > resultado = this.statusRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Status não encontrado!");
        }
        else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value="excstatus/{id}")
    public Status deleteById(@PathVariable Long id){
        Status status = findById(id);
        this.statusRepository.deleteById(id);
        return status;
    }

    @RequestMapping(value = "edstatus/{id}", method = RequestMethod.PUT)
    public Status updateById(@PathVariable Long id, @RequestBody Status status){
        this.findById(id);
        status.setId(id);
        status = this.statusRepository.save(status);
        return status;
    }

}




