package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import com.portal_tech.portal_tech.services.ChamadoService;
import com.portal_tech.portal_tech.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministradorController {

@Autowired
    private PessoaService pessoaService;
@Autowired
    private ChamadoService chamadoService;

@GetMapping("/adminpainel/{id}")
    public String adminPainel(Model model){
    List<ChamadoDTO> chamadoDTO = (List<ChamadoDTO>) chamadoService.findAllChamados();
    model.addAttribute("chamados", chamadoDTO);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<String> dataFormatada = new ArrayList<>();
    for (ChamadoDTO chamado : chamadoDTO){
        LocalDate dtAbertura = chamado.getDt_abertura();
        String dtFormatada = dtAbertura.format(formatter);
        dataFormatada.add(dtFormatada);
    }
    model.addAttribute("dtFormata", dataFormatada);
    return "index.admin";

}

}

