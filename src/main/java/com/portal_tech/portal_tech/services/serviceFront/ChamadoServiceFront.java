package com.portal_tech.portal_tech.services.serviceFront;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    public ChamadoDTO save(ChamadoDTO dto) {
        Chamado chamado = dto.convert(dto);
        chamado = chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }

    //Busca todos os chamados cadastrados no banco de dados.
    public List<ChamadoDTO> findChamadosSemTecnico() {
        List<Chamado> chamados = chamadoRepository.findChamadosSemTecnico();
        if (chamados.isEmpty()) {
            throw new RuntimeException("Não há chamados cadastrados!");
        } else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
            return (listChamadoDTO);
        }
    }

    //Busca todos os chamados cadastrados no banco de dados.
    public List<ChamadoDTO> findAllChamados() {
        List<Chamado> chamados = chamadoRepository.findAll();
        if (chamados.isEmpty()) {
            throw new RuntimeException("Não há chamados cadastrados!");
        } else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
            return listChamadoDTO;
        }
    }


    //Buscar um chamado pelo ID fornecido.
    public ChamadoDTO findById(Long id) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            return new ChamadoDTO(chamado);
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
    public ChamadoDTO updateById(Long id, ChamadoDTO dto) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            Chamado updatedChamado = dto.convert(dto);
            updatedChamado.setIdTecnico(dto.getId_tecnico());
            updatedChamado.setId(id);
            chamadoRepository.save(updatedChamado);
            return new ChamadoDTO(updatedChamado);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
        }
    }

    public List<ChamadoDTO> findById_Tecnico(Long id_tecnico) {
        List<Chamado> chamados = chamadoRepository.findById_Tecnico(id_tecnico);
        if (chamados.isEmpty()) {
            throw new RuntimeException("Não há chamados cadastrados!");
        } else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
            return listChamadoDTO;
        }

    }

    public ResponseEntity<List<ChamadoDTO>> findById_Usuario(Long id_usuario) {
        List<Chamado> chamados = chamadoRepository.findById_Usuario(id_usuario);
        if (chamados.isEmpty()) {
            throw new RuntimeException("Não há chamados cadastrados!");
        } else {
            List<ChamadoDTO> listChamadoDTO = chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(listChamadoDTO, HttpStatus.OK);
        }
    }

    public ChamadoDTO findById_Status(Long id_status) {
        List<Chamado> chamados = chamadoRepository.findById_Status(id_status);
        if (!chamados.isEmpty()) {
            Chamado chamado = chamados.get(0);
            return new ChamadoDTO(chamado);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id_status);
        }
    }

    public ChamadoDTO findById_Prioridade(Long id_prioridade) {
        List<Chamado> chamados = chamadoRepository.findById_Status(id_prioridade);
        if (!chamados.isEmpty()) {
            Chamado chamado = chamados.get(0);
            return new ChamadoDTO(chamado);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id_prioridade);
        }
    }

    public ChamadoDTO findById_Setor(Long id_setor) {
        List<Chamado> chamados = chamadoRepository.findById_Status(id_setor);
        if (!chamados.isEmpty()) {
            Chamado chamado = chamados.get(0);
            return new ChamadoDTO(chamado);
        } else {
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id_setor);
        }
    }


}
