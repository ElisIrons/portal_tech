package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public ResponseEntity<String> save() {
        List<SetorDTO> setor = new ArrayList<>();

        SetorDTO setor1 = new SetorDTO();
        setor1.setNome("Financeiro");
        setorRepository.save(SetorDTO.convert(setor1));

        return new ResponseEntity<>("arrumar este método", HttpStatus.CREATED);
    }

    public ResponseEntity<SetorDTO> findById(long id) {
        Optional<Setor> resultado = this.setorRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Setor não encontrado!");
        } else {
            return new ResponseEntity<>(new SetorDTO(resultado.get()), HttpStatus.OK);
        }
    }

    public ResponseEntity<List<SetorDTO>> findAll(){
        List<Setor> setores = setorRepository.findAll();
        if(setores.isEmpty()){
            throw new RuntimeException("Não há setores cadastrados!");
        } else {
            List<SetorDTO> listSetorDTO = setores.stream().map(SetorDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(listSetorDTO, HttpStatus.OK);
        }
    }
    
    public ResponseEntity<SetorDTO> updateById (long id, SetorDTO setorDTO){
      this.findById(id);
      setorDTO.setId(id);
     Setor setor = this.setorRepository.save(new Setor(setorDTO.getId(), setorDTO.getNome()));
      return new ResponseEntity<>(setorDTO, HttpStatus.CREATED);
    }
    
    public ResponseEntity<String> deleteById(long id){
        this.setorRepository.deleteById(id);
        return new ResponseEntity<>("setor deletado com sucesso", HttpStatus.OK);
    }
    
}