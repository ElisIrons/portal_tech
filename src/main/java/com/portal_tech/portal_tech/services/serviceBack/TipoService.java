package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public ResponseEntity<TipoDTO> save(TipoDTO dto){
        // só nas 2 linhas abaixo é q é usada a entidade em si, só nelas é feita a chamada pro bd
        Tipo tipo = TipoDTO.convert(dto); //convertendo tipo dto em tipo
        tipo = this.tipoRepository.save(tipo);
        return new ResponseEntity<>(new TipoDTO(tipo), HttpStatus.OK);
    }

    public ResponseEntity<List<TipoDTO>> findAll(){
        List<Tipo> tipos = tipoRepository.findAll();
        if (tipos.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há tipos de usuários cadastrados!");
        }
        else {
            List listTipoDTO = tipos.stream().map(TipoDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(listTipoDTO, HttpStatus.OK); //conver lst tipos em lst de tps dto
        }
    }

    public ResponseEntity<TipoDTO> findById(Long id){
        Optional<Tipo> resultado = this.tipoRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Tipo de usuário não encontrado!");
        }
        else {
            return new ResponseEntity<>(new TipoDTO(resultado.get()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }
    }


    public ResponseEntity<String> deleteById(Long id){
        this.tipoRepository.deleteById(id);
        return new ResponseEntity<>("Os dados foram deletados com sucesso", HttpStatus.OK);
    }

    public ResponseEntity<TipoDTO> updateById(Long id, TipoDTO dto){
        this.findById(id);
        Tipo tipo = TipoDTO.convert(dto);
        tipo.setId(id);
        tipo = this.tipoRepository.save(tipo);
        return new ResponseEntity<>(new TipoDTO(tipo), HttpStatus.OK);
    }

}

