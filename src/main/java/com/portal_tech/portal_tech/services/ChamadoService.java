package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private StatusService statusService;

    public ResponseEntity<ChamadoDTO> save(ChamadoDTO dto) {
        Chamado chamado =  ChamadoDTO.convert(dto);   //dto.convert(dto); // Chamar convert() usando a instância de ChamadoDTO
        System.out.println("chamado.getIdStatus() " + chamado.getIdStatus());
        chamado = this.chamadoRepository.save(chamado);
        return new ResponseEntity<>((new ChamadoDTO(chamado)), HttpStatus.CREATED);
    }

    public List<ChamadoDTO> findAllChamados() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());

            return listChamadoDTO;//converte lst chamados em lst de chamados dto
        }
    }

    public List<ChamadoDTO> findChamadosSemTecnico() {
        List<Chamado> chamados = this.chamadoRepository.findChamadosSemTecnico();
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());

            return listChamadoDTO;//converte lst chamados em lst de chamados dto
        }
    }
    public ResponseEntity<ChamadoDTO> findById(Long id) {
        Optional<Chamado> optionalChamado = this.chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.OK);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
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

    public ResponseEntity<String> deleteById(Long id) {
        this.chamadoRepository.deleteById(id);
        return new ResponseEntity<>("chamado deletado com sucesso", HttpStatus.OK);
    }


    public ResponseEntity<ChamadoDTO> updateById(Long id, ChamadoDTO dto) {
        this.findById(id);

        Chamado chamado = dto.convert(dto);

        chamado.setId(id);
        this.chamadoRepository.save(chamado);
        return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.CREATED);
    }

    public List<Chamado> buscarChamados() {
        return chamadoRepository.findAll();
    }

    public List<ChamadoDTO> findAll() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }
}
