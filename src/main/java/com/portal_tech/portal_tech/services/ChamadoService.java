package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private StatusService statusService;

    public ChamadoDTO save(ChamadoDTO dto) {
        Chamado chamado = dto.convert(dto); // Chamar convert() usando a instância de ChamadoDTO
        chamado = this.chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }

    public List<ChamadoDTO> findAll() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }

    public ChamadoDTO findById(Long id) {
        Optional<Chamado> chamado = this.chamadoRepository.findById(id);
        if(chamado.isEmpty()){
            throw new RuntimeException("O chamado não foi encontrado!");
        }
        else {
            return new ChamadoDTO(chamado.get());
        }
    }

    public ChamadoDTO deleteById(Long id) {
        ChamadoDTO dto = findById(id);
        this.chamadoRepository.deleteById(id);
        return dto;
    }

    public ChamadoDTO updateById(Long id, ChamadoDTO dto) {
        this.findById(id);

        Chamado chamado = dto.convert(dto);

        chamado.setId(id);
        this.chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }




}
