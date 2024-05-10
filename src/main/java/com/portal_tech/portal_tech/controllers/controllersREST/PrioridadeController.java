package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.services.PrioridadeService;
import com.portal_tech.portal_tech.swaggerDoc.PrioridadeControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController implements PrioridadeControllerOpenApi {

    @Autowired
    private PrioridadeService prioridadeService;


    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public void criarPrioridades() {
        this.prioridadeService.CriaPrioridade();

    }

    /*@RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public Prioridade save (@RequestBody Prioridade prioridade) {
        return this.prioridadeService.save(prioridade);
    }*/


    @RequestMapping(value = "/mostrar", method = RequestMethod.GET)
    public List<PrioridadeDTO> findAll(){
        return this.prioridadeService.findAll();
    }


    @RequestMapping(value = "/mostrar/{id}", method = RequestMethod.GET)
    public PrioridadeDTO findById(@PathVariable Long id) {
        return this.prioridadeService.findById(id);
    }


    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public PrioridadeDTO deleteById(@PathVariable Long id) {
        return this.prioridadeService.deleteById(id);
    }


    @RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
    public PrioridadeDTO updateById(@PathVariable Long id, @RequestBody PrioridadeDTO prioridade) {
        return this.prioridadeService.updateById(id, prioridade);
    }

}