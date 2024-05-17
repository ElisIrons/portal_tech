package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.services.serviceBack.ChamadoService;
import com.portal_tech.portal_tech.services.serviceFront.ChamadoServiceFront;
import com.portal_tech.portal_tech.services.serviceBack.PessoaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdministradorController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PrioridadeRepository prioridadeRepository;


    @GetMapping("/adminpainel")
    public String findById_Tecnico(Model model, HttpSession session) {
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);

        List<Pessoa> tecnicos = chamadoServiceFront.findAllTecnicos();
        model.addAttribute("tecnicos", tecnicos);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();

        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
            dataFormatada.add(dtFormatada);

            LocalDate dtFim = chamado.getDt_fim();
            if (dtFim != null) {
                String dtFimFormatada = dtFim.format(formatter);
                dataFimFormatada.add(dtFimFormatada);
            } else {
                dataFimFormatada.add("");
            }
        }
        model.addAttribute("dtFormatada", dataFormatada);
        model.addAttribute("dtFimFormatada", dataFimFormatada);


        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        String nomeUsuario = userOn.getNome();
        model.addAttribute("userOn", userOn);
        return "tela.admin";
    }


    @PostMapping("/adminpainel")
    public String chamados(@RequestParam("prioridade") String prioridade,
                           @RequestParam Long id_chamado,
                           @RequestParam("status")String status,
                           @RequestParam(required = false) Long id_tecnico,
                           HttpSession session) {

        Chamado chamado = this.buscaChamado(id_chamado);

        Prioridade prioridademodified = null;
        Prioridade prioridadeBd = chamado.getIdPrioridade();
        switch (prioridade) {
            case "baixa":
                prioridademodified = this.prioridadeRepository.findById(1L).orElse(null);
                break;
            case "media":
                prioridademodified = this.prioridadeRepository.findById(2L).orElse(null);
                break;
            case "alta":
                prioridademodified = this.prioridadeRepository.findById(3L).orElse(null);
                break;
            default:
                return "redirect:/adminpainel";
        }

        Status statusmodified = null;
        Status statusbd = chamado.getIdStatus();
        switch (status) {
            case "em-analise":
                statusmodified = this.statusRepository.findById(1L).orElse(null);
                chamado.setDt_fim(null);
                break;
            case "aguardando":
                statusmodified = this.statusRepository.findById(2L).orElse(null);
                chamado.setDt_fim(null);
                break;
            case "em-atendimento":
                statusmodified = this.statusRepository.findById(3L).orElse(null);
                chamado.setDt_fim(null);
                break;
            case "outro-setor":
                statusmodified = this.statusRepository.findById(4L).orElse(null);
                chamado.setDt_fim(null);
                break;
            case "finalizado":
                statusmodified = this.statusRepository.findById(5L).orElse(null);
                chamado.setDt_fim(LocalDate.now());
                break;
            default:
                return "redirect:/adminpainel";
        }

        Pessoa tecnicomodified = null;
        Pessoa tecnicobd = chamado.getIdTecnico();

        if(id_tecnico != null){
            tecnicomodified = this.pessoaRepository.findById(id_tecnico).orElse(null);
        }

        if ( prioridademodified != null || statusmodified != null || tecnicomodified != null) {
            chamado.setIdPrioridade(prioridademodified);
            chamado.setIdStatus(statusmodified);
            chamado.setIdTecnico(tecnicomodified);
            chamadoRepository.save(chamado);
        }
        else{
            chamado.setIdPrioridade(prioridadeBd);
            chamado.setIdStatus(statusbd);
            chamado.setIdTecnico(tecnicobd);
        }
        return "redirect:adminpainel";
    }


    public Chamado buscaChamado(Long id) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        return optionalChamado.orElse(null);
    }

}