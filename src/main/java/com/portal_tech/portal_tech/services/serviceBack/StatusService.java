package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public ResponseEntity<StatusDTO> save(StatusDTO dto){
        // só nas 2 linhas abaixo é q é usada a entidade em si, só nelas é feita a chamada pro bd
        Status status = StatusDTO.convert(dto); //convertendo tipo dto em tipo
        status = this.statusRepository.save(status);
        return new ResponseEntity<>(new StatusDTO(status), HttpStatus.CREATED);
    }

    public ResponseEntity<List<StatusDTO>> findAll(){
        List<Status> tipos = statusRepository.findAll();
        if (tipos.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há status cadastrados!");
        }
        else {
            return new ResponseEntity<>(tipos.stream().map(StatusDTO::new).collect(Collectors.toList()), HttpStatus.OK); //conver lst tipos em lst de tps dto
        }
    }

    public ResponseEntity<StatusDTO> findById(Long id){
        Optional<Status> resultado = this.statusRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Status não encontrado!");
        }
        else {
            return new ResponseEntity<>(new StatusDTO(resultado.get()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }
    }


    public ResponseEntity<String> deleteById(Long id){
        this.statusRepository.deleteById(id);
        return new ResponseEntity<>("Status deletado com sucesso!",HttpStatus.OK);
    }

    public ResponseEntity<StatusDTO> updateById(Long id, StatusDTO dto){
        this.findById(id);
        Status status = StatusDTO.convert(dto);
        status.setId(id);
        status = this.statusRepository.save(status);
        return new ResponseEntity<>(new StatusDTO(status), HttpStatus.CREATED);
    }

}
