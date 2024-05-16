package com.portal_tech.portal_tech.services.serviceBack;


import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
import com.portal_tech.portal_tech.models.Chamado;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;


@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private StatusService statusService;

    public ResponseEntity<ChamadoDTO> save(ChamadoDTO dto) {
        Chamado chamado =  ChamadoDTO.convert(dto);   //dto.convert(dto); // Chamar convert() usando a instância de ChamadoDTO
        try {
            if(chamado.getDescricao() == null ){
                throw new UnprocessableEntityException422("O chamado esta vazio");
            }

            chamado = this.chamadoRepository.save(chamado);
            return new ResponseEntity<>((new ChamadoDTO(chamado)), HttpStatus.CREATED);

        }catch (UnprocessableEntityException422 e) {
            throw new UnprocessableEntityException422(e.getMessage());

        }
        catch (ExceptionHandler500 e){
            throw new ExceptionHandler500("Erro ao salvar o chamado");
        }
    }

    public List<ChamadoDTO> findAllChamados() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        try {
            if (chamados.isEmpty()) {
                throw new UnprocessableEntityException422("Não há chamados cadastrados!");
            } else {
                List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());

                return listChamadoDTO;//converte lst chamados em lst de chamados dto
            }
        }catch (UnsupportedOperationException e){
            throw new UnprocessableEntityException422(e.getMessage());
        }catch (Exception e){
            throw new ExceptionHandler500("Erro ao tentar loxalizar os chamados", e);
        }
    }


    public ResponseEntity<ChamadoDTO> findById(Long id) {
        Optional<Chamado> optionalChamado = this.chamadoRepository.findById(id);
        try {
            if (optionalChamado.isPresent()) {
                Chamado chamado = optionalChamado.get();
                return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.OK);
            } else {
                throw new UnprocessableEntityException422("O chamado não foi encontrado! ID: " + id);
            }
        }catch (UnsupportedOperationException e){
            throw new UnprocessableEntityException422(e.getMessage());
        }catch (Exception e){
            throw new ExceptionHandler500(format("Não foi possível localizar o ID passado, ID = %s",id), e);
        }

    }

    public ResponseEntity<String> deleteById(Long id) {
        try {
            this.findById(id);
            this.chamadoRepository.deleteById(id);
            return new ResponseEntity<>("chamado deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro ao deletar a pessoa ");
        }
    }

    public ResponseEntity<ChamadoDTO> updateById(Long id, ChamadoDTO dto) {
        try {

            Chamado chamado = this.chamadoRepository.findById(id).orElseThrow(() -> new UnprocessableEntityException422("Pessoa não encontrada"));

            Chamado chamadoNovo = dto.convert(dto);
            chamadoNovo.setId(id);
            this.chamadoRepository.save(chamadoNovo);
            return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.CREATED);
        } catch (UnprocessableEntityException422 e) {
            throw new UnprocessableEntityException422("Erro ao atualizar os dados da pessoa");

        } catch (Exception e) {
            throw new ExceptionHandler500("Erro ao atualizar os dados da pessoa");
        }
    }

}
