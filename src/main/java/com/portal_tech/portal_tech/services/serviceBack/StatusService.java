package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
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
      try {
          Status status = StatusDTO.convert(dto); //convertendo tipo dto em tipo
          status = this.statusRepository.save(status);
          return new ResponseEntity<>(new StatusDTO(status), HttpStatus.CREATED);
      }catch (Exception e){
          throw new ExceptionHandler500("Erro no servidor ao salvar o status novo adicionado");
      }
    }

    public ResponseEntity<List<StatusDTO>> findAll() {
        try {
            List<Status> tipos = statusRepository.findAll();
            if (tipos.isEmpty()) { //se estiver vazio, não encontrou nada cadastrado
                throw new UnprocessableEntityException422("Não há status cadastrados!");
            } else {
                return new ResponseEntity<>(tipos.stream().map(StatusDTO::new).collect(Collectors.toList()), HttpStatus.OK); //conver lst tipos em lst de tps dto
            }
        } catch (UnprocessableEntityException422 e) {
            throw new UnprocessableEntityException422(e.getMessage());
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro no servidor ao encontrar todos os status");
        }
    }

    public ResponseEntity<StatusDTO> findById(Long id){
        try {
            Optional<Status> resultado = this.statusRepository.findById(id);
            if (resultado.isEmpty()) {
                throw new UnprocessableEntityException422("Status não encontrado!");
            } else {
                return new ResponseEntity<>(new StatusDTO(resultado.get()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
            }
        }catch (UnprocessableEntityException422 e){
            throw new UnprocessableEntityException422(e.getMessage());
        }catch (Exception e){
            throw  new ExceptionHandler500("Erro no servidor");
        }
    }


    public ResponseEntity<String> deleteById(Long id){
        try {
            this.statusRepository.deleteById(id);
            return new ResponseEntity<>("Status deletado com sucesso!", HttpStatus.OK);
        }catch (Exception e){
            throw new ExceptionHandler500("Erro no servidor ao salvar" + e);
        }
    }

    public ResponseEntity<StatusDTO> updateById(Long id, StatusDTO dto) {
        try {
            this.findById(id);
            Status status = StatusDTO.convert(dto);
            status.setId(id);
            status = this.statusRepository.save(status);
            return new ResponseEntity<>(new StatusDTO(status), HttpStatus.CREATED);
        }catch (Exception e){
            throw new ExceptionHandler500(("Erro no servidor ao salvar" + e));
        }
    }

}
