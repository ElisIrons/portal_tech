package com.portal_tech.portal_tech.controllers.controllersREST;


import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.services.TipoService;
import com.portal_tech.portal_tech.swaggerDoc.TipoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tipo")
public class TipoContoller implements TipoControllerOpenApi {

    @Autowired
    private TipoService tipoService;

    @PostMapping("/save")
    public ResponseEntity<TipoDTO> save(@RequestBody TipoDTO tipoDTO){

        return this.tipoService.save(tipoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TipoDTO>> findAll(){
            return this.tipoService.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<TipoDTO> findById(@PathVariable Long id){
       return this.tipoService.findById(id);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return this.tipoService.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoDTO> updateById(@PathVariable Long id, @RequestBody TipoDTO tipoDTO){
        return this.tipoService.updateById(id, tipoDTO);
    }


}
