package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import com.portal_tech.portal_tech.services.StatusService;
import com.portal_tech.portal_tech.swaggerDoc.ChamadoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
public class ChamadoController implements ChamadoControllerOpenApi {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private StatusService statusService;

    @PostMapping
    public ResponseEntity<ChamadoDTO> save(@RequestBody ChamadoDTO dto){

        return this.chamadoService.save(dto);
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return (ResponseEntity<List<ChamadoDTO>>) this.chamadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id){
        return this.chamadoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return this.chamadoService.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<ChamadoDTO> updateById(@PathVariable Long id, @RequestBody ChamadoDTO dto){
        return this.chamadoService.updateById(id, dto);
    }
}

