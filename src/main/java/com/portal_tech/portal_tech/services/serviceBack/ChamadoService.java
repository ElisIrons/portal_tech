package com.portal_tech.portal_tech.services;


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
                throw new UnsupportedOperationException("Não há chamados cadastrados!");
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

    public List<ChamadoDTO> findById_Tecnico(Long id_tecnico) {
            List<Chamado> chamados = this.chamadoRepository.findById_Tecnico(id_tecnico);
            if (chamados.isEmpty()){
                throw new RuntimeException("Não há chamados cadastrados!");
            }
            else {
                //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
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


    public List<ChamadoDTO> findById_Usuario(Long id_usuario) {
        List<Chamado> chamados = this.chamadoRepository.findById_Usuario(id_usuario);
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
        }
    }

    public List<ChamadoDTO> findById_Status(Long id_status) {
        List<Chamado> chamados = this.chamadoRepository.findById_Status(id_status);
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
        }
    }

    public List<ChamadoDTO> findById_Prioridade(Long id_prioridade) {
        List<Chamado> chamados = this.chamadoRepository.findById_Prioridade(id_prioridade);
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
        }
    }

    public List<ChamadoDTO> findById_Setor(Long id_setor) {
        List<Chamado> chamados = this.chamadoRepository.findById_Setor(id_setor);
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
        }
    }





    public List<Chamado> buscarChamados() {
        return chamadoRepository.findAll();
    }

    public List<ChamadoDTO> findAll() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }
}
