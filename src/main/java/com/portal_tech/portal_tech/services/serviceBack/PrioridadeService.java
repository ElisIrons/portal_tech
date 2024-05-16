package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrioridadeService {

    @Autowired
    private PrioridadeRepository prioridadeRepository;


    public PrioridadeService(PrioridadeRepository prioridadeRepository) {
        this.prioridadeRepository = prioridadeRepository;
    }

    public void CriaPrioridade() {

        List<Prioridade> prioridades = List.of(
                new Prioridade("Alta"),
                new Prioridade("Média"),
                new Prioridade("Baixa")
        );
        // Salva as novas prioridades utilizando o método save
        prioridadeRepository.saveAll(prioridades);
    }


    /*public Prioridade save(Prioridade prioridade){
         O método save é utilizado tanto para criar quanto para atualizar prioridades
        return this.prioridadeRepository.save(prioridade);
    }*/


    public ResponseEntity<List<PrioridadeDTO>> findAll(){
        List<Prioridade> prioridades = this.prioridadeRepository.findAll();
        List<PrioridadeDTO>listOfPrioridades = prioridades.stream().map(PrioridadeDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(listOfPrioridades, HttpStatus.OK);
    }


    public ResponseEntity<PrioridadeDTO> findById(Long id) {
        Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Prioridade não encontrada.");
        } else {
            return new ResponseEntity<>( new PrioridadeDTO(resultado.get()), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> deleteById(Long id) {
        PrioridadeDTO prioridade = findById(id).getBody();
        this.prioridadeRepository.deleteById(id);
        return new ResponseEntity<>("elemento deletado com sucesso", HttpStatus.OK);
    }

    public ResponseEntity<PrioridadeDTO> updateById(Long id, PrioridadeDTO dto) {
        this.findById(id);
        Prioridade prioridade = PrioridadeDTO.convert(dto);
        prioridade.setId(id);
        prioridade= this.prioridadeRepository.save(prioridade);
        return new ResponseEntity<>(new PrioridadeDTO(prioridade), HttpStatus.CREATED);
    }

}