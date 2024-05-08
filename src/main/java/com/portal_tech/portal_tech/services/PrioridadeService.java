package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


    public List<PrioridadeDTO> findAll(){
        List<Prioridade> prioridades = this.prioridadeRepository.findAll();
        return prioridades.stream().map(PrioridadeDTO::new).collect(Collectors.toList());
    }


    public PrioridadeDTO findById(Long id) {
        Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new RuntimeException("Prioridade não encontrada.");
        } else {
            return new PrioridadeDTO(resultado.get());
        }
    }

    public PrioridadeDTO deleteById(Long id) {
        PrioridadeDTO prioridade = findById(id);
        this.prioridadeRepository.deleteById(id);
        return prioridade;
    }

    public PrioridadeDTO updateById(Long id, PrioridadeDTO dto) {
        this.findById(id);
        Prioridade prioridade = PrioridadeDTO.convert(dto);
        prioridade.setId(id);
        prioridade= this.prioridadeRepository.save(prioridade);
        return new PrioridadeDTO(prioridade);
    }

}