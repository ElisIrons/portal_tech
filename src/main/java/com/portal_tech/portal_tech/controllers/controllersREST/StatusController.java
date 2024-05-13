package com.portal_tech.portal_tech.controllers.controllersREST;

import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.services.StatusService;
import com.portal_tech.portal_tech.swaggerDoc.StatusControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController implements StatusControllerOpenApi {

    @Autowired
    private StatusService statusService;
    @PostMapping("/save")
    public ResponseEntity<StatusDTO> save(@RequestBody StatusDTO statusDTO){
        return this.statusService.save(statusDTO);
    }

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public ResponseEntity<List<StatusDTO>> findAll(){
      return this.statusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id){
     return this.statusService.findById(id);
    }

    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return  this.statusService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<StatusDTO> updateById(@PathVariable Long id, @RequestBody StatusDTO statusDTO){
        return this.statusService.updateById(id,statusDTO);
    }

}




