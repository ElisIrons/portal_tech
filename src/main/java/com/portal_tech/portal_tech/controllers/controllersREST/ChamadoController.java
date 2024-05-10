package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import com.portal_tech.portal_tech.services.StatusService;
import com.portal_tech.portal_tech.swaggerDoc.ChamadoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/chamado")
public class ChamadoController implements ChamadoControllerOpenApi {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private StatusService statusService;

    @PostMapping
    public ChamadoDTO save(@RequestBody ChamadoDTO dto){
        return this.chamadoService.save(dto);
    }

    @GetMapping
    public List<ChamadoDTO> findAll(){
        return this.chamadoService.findAll();
    }

    @GetMapping("/{id}")
    public ChamadoDTO findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ChamadoDTO deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public ChamadoDTO updateById(@PathVariable Long id, @RequestBody ChamadoDTO dto){
        return this.chamadoService.updateById(id, dto);
    }
}

