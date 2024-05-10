package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<SetorDTO> save() {
        List<SetorDTO> setor = new ArrayList<>();

        SetorDTO setor1 = new SetorDTO();
        setor1.setNome("Financeiro");
        setorRepository.save(SetorDTO.convert(setor1));

        return setor;
    }

    public SetorDTO findById(long id) {
        Optional<Setor> resultado = this.setorRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Setor não encontrado!");
        } else {
            return new SetorDTO(resultado.get());
        }
    }

    public List<SetorDTO> findAll(){
        List<Setor> setores = setorRepository.findAll();
        if(setores.isEmpty()){
            throw new RuntimeException("Não há setores cadastrados!");
        } else {
            return setores.stream().map(SetorDTO::new).collect(Collectors.toList());
        }
    }
    
    public SetorDTO updateById (long id, SetorDTO setorDTO){
      this.findById(id);
      setorDTO.setId(id);
     Setor setor = this.setorRepository.save(new Setor(setorDTO.getId(), setorDTO.getNome()));
      return setorDTO;
    }
    
    public SetorDTO deleteById(long id){
        SetorDTO setorDTO = findById(id);
        this.setorRepository.deleteById(id);
        return setorDTO;
    }
    
}