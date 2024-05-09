package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public StatusDTO save(StatusDTO dto){
        // só nas 2 linhas abaixo é q é usada a entidade em si, só nelas é feita a chamada pro bd
        Status status = StatusDTO.convert(dto); //convertendo tipo dto em tipo
        status = this.statusRepository.save(status);
        return new StatusDTO(status);
    }

    public List<StatusDTO> findAll(){
        List<Status> tipos = statusRepository.findAll();
        if (tipos.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há status cadastrados!");
        }
        else {
            return tipos.stream().map(StatusDTO::new).collect(Collectors.toList()); //conver lst tipos em lst de tps dto
        }
    }

    public StatusDTO findById(Long id){
        Optional<Status> resultado = this.statusRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Status não encontrado!");
        }
        else {
            return new StatusDTO(resultado.get()); //usei get para retornar o objeto dentro de Optional
        }
    }


    public StatusDTO deleteById(Long id){
        StatusDTO dto = this.findById(id);
        this.statusRepository.deleteById(id);
        return dto;
    }

    public StatusDTO updateById(Long id, StatusDTO dto){
        this.findById(id);
        Status status = StatusDTO.convert(dto);
        status.setId(id);
        status = this.statusRepository.save(status);
        return new StatusDTO(status);
    }

}
