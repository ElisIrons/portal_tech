package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public TipoDTO save(TipoDTO dto){
        // só nas 2 linhas abaixo é q é usada a entidade em si, só nelas é feita a chamada pro bd
        Tipo tipo = TipoDTO.convert(dto); //convertendo tipo dto em tipo
        tipo = this.tipoRepository.save(tipo);
        return new TipoDTO(tipo);
    }

    public List<TipoDTO> findAll(){
        List<Tipo> tipos = tipoRepository.findAll();
        if (tipos.isEmpty()){ //se estiver vazio, não encontrou nada cadastrado
            throw new RuntimeException("Não há tipos de usuários cadastrados!");
        }
        else {
            return tipos.stream().map(TipoDTO::new).collect(Collectors.toList()); //conver lst tipos em lst de tps dto
        }
    }

    public TipoDTO findById(Long id){
        Optional<Tipo> resultado = this.tipoRepository.findById(id);
        if (resultado.isEmpty()){
            throw new RuntimeException("Tipo de usuário não encontrado!");
        }
        else {
            return new TipoDTO(resultado.get()); //usei get para retornar o objeto dentro de Optional
        }
    }


    public TipoDTO deleteById(Long id){
        TipoDTO dto = this.findById(id);
        this.tipoRepository.deleteById(id);
        return dto;
    }

    public TipoDTO updateById(Long id, TipoDTO dto){
        this.findById(id);
        Tipo tipo = TipoDTO.convert(dto);
        tipo.setId(id);
        tipo = this.tipoRepository.save(tipo);
        return new TipoDTO(tipo);
    }

}

