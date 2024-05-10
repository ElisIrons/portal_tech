package com.portal_tech.portal_tech.controllers.controllersREST;


import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import com.portal_tech.portal_tech.swaggerDoc.TipoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TipoContoller implements TipoControllerOpenApi {

    @Autowired
    private TipoRepository tipoRepository;

    @RequestMapping(value="cadtipo", method = RequestMethod.POST)
    public Tipo save(@RequestBody Tipo tipo){
        return tipoRepository.save(tipo);
    }

    @RequestMapping(value="tipo", method = RequestMethod.GET)
    public List<Tipo> findAll(){
        List<Tipo> tipos = tipoRepository.findAll();
        if (tipos.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há tipos de usuários cadastrados!");
        }
        else {
            return tipos;
        }
    }

    @RequestMapping(value="tipo/{id}", method = RequestMethod.GET)
    public Tipo findById(@PathVariable Long id){
        Optional<Tipo> resultado = this.tipoRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Tipo de usuário não encontrado!");
        }
        else {
            return resultado.get(); //usei get para retornar o objeto dentro de Optional
        }
    }

    @DeleteMapping(value="exctipo/{id}")
    public Tipo deleteById(@PathVariable Long id){
        Tipo tipo = findById(id);
        this.tipoRepository.deleteById(id);
        return tipo;
    }

    @RequestMapping(value = "edtipo/{id}", method = RequestMethod.PUT)
    public Tipo updateById(@PathVariable Long id, @RequestBody Tipo tipo){
        this.findById(id);
        tipo.setId(id);
        tipo = this.tipoRepository.save(tipo);
        return tipo;
    }


}
