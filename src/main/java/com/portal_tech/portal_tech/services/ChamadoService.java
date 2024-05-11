package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.StatusDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
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
        System.out.println("chamado.getIdStatus() " + chamado.getIdStatus());
        chamado = this.chamadoRepository.save(chamado);
        return new ChamadoDTO(chamado);
    }

    public List<ChamadoDTO> findAllChamados() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        if (chamados.isEmpty()){
            throw new RuntimeException("Não há chamados cadastrados!");
        }
        else {
            //DateTimeFormatter dtFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());//converte lst chamados em lst de chamados dto
        }
    }
    public ChamadoDTO findById(Long id) {
        Optional<Chamado> optionalChamado = this.chamadoRepository.findById(id);
        if (optionalChamado.isPresent()) {
            Chamado chamado = optionalChamado.get();
            return new ChamadoDTO(chamado);
        }
        else{
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
        }
        //Pessoa usuario = new Pessoa(chamado.get().getIdUsuario().getId());
        //Pessoa tecnico = new Pessoa(chamado.get().getIdTecnico().getId());
        //Status status = new Status(chamado.get().getIdStatus().getId());
        //Prioridade prioridade = new Prioridade(chamado.get().getIdPrioridade().getId());
/*        if(chamado.isEmpty()){
            throw new RuntimeException("O chamado não foi encontrado! ID: " + id);
        }
        else {
            return new ChamadoDTO(chamado.get());
        }*/

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

    public List<Chamado> buscarChamados() {
        return chamadoRepository.findAll();
    }

    public List<ChamadoDTO> findAll() {
        List<Chamado> chamados = this.chamadoRepository.findAll();
        return chamados.stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }
}
