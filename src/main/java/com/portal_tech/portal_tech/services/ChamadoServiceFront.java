package com.portal_tech.portal_tech.services;

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

@Service
public class ChamadoServiceFront {

    @Autowired
    private ChamadoRepository chamadoRepository;

    //Salva um novo chamado no banco de dados.
    public ResponseEntity<ChamadoDTO> save(ChamadoDTO dto) {
        Chamado chamado = dto.convert(dto);
        chamado = chamadoRepository.save(chamado);
        return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.CREATED);
    }

    //Busca todos os chamados cadastrados no banco de dados.
    public ResponseEntity<List<ChamadoDTO>> findAllChamados() {
        List<Chamado> chamados = chamadoRepository.findAll();
        if (chamados.isEmpty()) {
            throw new RuntimeException("Não há chamados cadastrados!");
        } else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(listChamadoDTO, HttpStatus.OK);
        }
    }

    //Buscar um chamado pelo ID fornecido.
    public ResponseEntity<ChamadoDTO> findById(Long id) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            return new ResponseEntity<>(new ChamadoDTO(chamado), HttpStatus.OK);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
        }
    }

    //Exclui um chamado pelo ID fornecido
    public ResponseEntity<String> deleteById(Long id) {
        chamadoRepository.deleteById(id);
        return new ResponseEntity<>("Chamado deletado com sucesso", HttpStatus.OK);
    }

    //Atualiza um chamado existente com base no ID fornecido.
    public ResponseEntity<ChamadoDTO> updateById(Long id, ChamadoDTO dto) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            Chamado updatedChamado = dto.convert(dto);
            updatedChamado.setId(id);
            chamadoRepository.save(updatedChamado);
            return new ResponseEntity<>(new ChamadoDTO(updatedChamado), HttpStatus.CREATED);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
        }
    }

}
