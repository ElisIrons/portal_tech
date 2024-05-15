package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import com.portal_tech.portal_tech.services.ChamadoServiceFront;
import com.portal_tech.portal_tech.services.PessoaService;
import jakarta.servlet.http.HttpSession;
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


@Autowired
    private ChamadoServiceFront chamadoServiceFront;

@GetMapping ("/adminpainel")
    public String findAllChamados(Model model, HttpSession session){
    List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
    model.addAttribute("chamados", chamadoDTO);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<String>dataFormatada = new ArrayList<>();
    List<String>dataFimFormatada = new ArrayList<>();

for (ChamadoDTO chamado : chamadoDTO){
    LocalDate dtAbertura = chamado.getDt_abertura();
    String dtFormatada = dtAbertura.format(formatter);
    dataFimFormatada.add(dtFormatada);

    LocalDate dtFim = chamado.getDt_fim();
    if (dtFim != null){
        String dtFimFormatada = dtFim.format(formatter);
        dataFimFormatada.add(dtFimFormatada);
    } else {
        dataFimFormatada.add("");
    }
}
model.addAttribute("dtFormatada", dataFormatada);
model.addAttribute("dtFimFormata", dataFimFormatada);

Pessoa userOn = (Pessoa) session.getAttribute("cache");
String nomeUsuario = userOn.getNome();
model.addAttribute("userOn", userOn);
return "tela.admin";
}

//
//@GetMapping("/adminpainel")
//    public String adminPainel(Model model){
//    List<ChamadoDTO> chamadoDTO = (List<ChamadoDTO>) chamadoService.findAllChamados();
//    model.addAttribute("chamados", chamadoDTO);
//
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    List<String> dataFormatada = new ArrayList<>();
//    for (ChamadoDTO chamado : chamadoDTO){
//        LocalDate dtAbertura = chamado.getDt_abertura();
//        String dtFormatada = dtAbertura.format(formatter);
//        dataFormatada.add(dtFormatada);
//    }
//    model.addAttribute("dtFormata", dataFormatada);
//    return "index.admin";
//
//}

}

