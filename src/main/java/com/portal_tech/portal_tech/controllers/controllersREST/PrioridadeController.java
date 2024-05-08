package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController {

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @RequestMapping(value = "/criar", method = RequestMethod.POST)
    public void criarPrioridades() {
        List<Prioridade> prioridades = List.of(
                new Prioridade("Alta"),
                new Prioridade("Média"),
                new Prioridade("Baixa")
        );
        prioridadeRepository.saveAll(prioridades);
    }


    @RequestMapping(value = "/mostrar", method = RequestMethod.GET)
    public List<Prioridade> findAll(){
        List<Prioridade> prioridades = this.prioridadeRepository.findAll();
        return prioridades;
    }


    @RequestMapping(value = "/mostrar/{id}", method = RequestMethod.GET)
    public Prioridade findById(@PathVariable long id) {
        Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Prioridade não encontrada.");
        } else {
            return resultado.get();
        }
    }


    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.DELETE)
    public Prioridade deletebyId(@PathVariable long id) {
        Prioridade prioridade = findById(id);
        this.prioridadeRepository.deleteById(id);
        return prioridade;
    }


    @RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
    public Prioridade updateById(@PathVariable long id, @RequestBody Prioridade prioridade) {
        this.findById(id);
        prioridade.setId(id);
        prioridade = this.prioridadeRepository.save(prioridade);
        return prioridade;
    }

}

