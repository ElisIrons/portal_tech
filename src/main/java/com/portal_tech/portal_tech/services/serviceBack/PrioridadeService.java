package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
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

import static java.lang.String.format;

@Service
public class PrioridadeService {

    @Autowired
    private PrioridadeRepository prioridadeRepository;


    public PrioridadeService(PrioridadeRepository prioridadeRepository) {
        this.prioridadeRepository = prioridadeRepository;
    }

    public ResponseEntity<PrioridadeDTO> CriaPrioridade(PrioridadeDTO prioridadeDTO) {

        Prioridade prioridades = PrioridadeDTO.convert(prioridadeDTO);
        try {

            prioridadeRepository.save(prioridades);
            return new ResponseEntity<>(new PrioridadeDTO(prioridades), HttpStatus.CREATED);
        }catch (UnprocessableEntityException422 e){
            throw new UnprocessableEntityException422("Faltam dados obrigatórios a serem passados/não foi possível salvar");
        }catch (Exception e){
            throw new ExceptionHandler500("Erro no servidor ao salvar");
        }

    }


    public ResponseEntity<List<PrioridadeDTO>> findAll(){
        try {
            List<Prioridade> prioridades = this.prioridadeRepository.findAll();
            List<PrioridadeDTO> listOfPrioridades = prioridades.stream().map(PrioridadeDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(listOfPrioridades, HttpStatus.OK);
        }catch (Exception e){
            throw new ExceptionHandler500("Erro no servidor ao tentar buscar as listas");
        }
    }


    public ResponseEntity<PrioridadeDTO> findById(Long id) {
        try {
            Optional<Prioridade> resultado = this.prioridadeRepository.findById(id);

            if (resultado.isEmpty()) {
                throw new UnprocessableEntityException422("Não foram encontradas informações de prioridade com ID " + id);
            } else {
                return new ResponseEntity<>(new PrioridadeDTO(resultado.get()), HttpStatus.OK);
            }

            }catch(UnprocessableEntityException422 e){
            throw  new UnprocessableEntityException422(e.getMessage());
        }catch (Exception e){
            throw  new ExceptionHandler500("Erro no servidor" + e);
        }
    }

    public ResponseEntity<String> deleteById(Long id) {
        try {

            this.prioridadeRepository.deleteById(id);
            return new ResponseEntity<>("elemento deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro nos ervidor ao deletar o elemento");
        }
    }

    public ResponseEntity<PrioridadeDTO> updateById(Long id, PrioridadeDTO dto) {
        try {
            this.findById(id);
            Prioridade prioridade = PrioridadeDTO.convert(dto);
            prioridade.setId(id);
            prioridade = this.prioridadeRepository.save(prioridade);
            return new ResponseEntity<>(new PrioridadeDTO(prioridade), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ExceptionHandler500(format("Não foi possível localizar o ID passado, ID = %s", id), e);
        }
    }

}