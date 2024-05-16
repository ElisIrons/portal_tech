package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.services.serviceFront.ChamadoServiceFront;


import com.portal_tech.portal_tech.swaggerDoc.SetorControllerOpenApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;


    @GetMapping("/tela-usuario")
    public String exibirTelaUsuario(Model model, HttpSession session) {
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        String nomeUsuario = userOn.getNome();
        model.addAttribute("userOn", userOn);
        return "tela.usuario";
    }

    @GetMapping("/usuario/novo/chamado")
    public String novaTelaChamado(Model model, HttpSession session) {
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        model.addAttribute("userOn", userOn);
        return "usuario.novo.chamado";
    }

    public Chamado buscaChamado (Long id){
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        return optionalChamado.orElse(null);
    }

    @PostMapping("/usuario/novo/chamado")
    public String chamados ( @RequestParam("status") String status,
                             @RequestParam Long id_status,
                             @RequestParam("prioridade") String prioridade,
                             @RequestParam Long id_chamado,
                             @RequestParam int id,
                             HttpSession session){
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
                return "redirect:usuario.novo.chamado";
        }



        Status statusmodified = null;
        Status statusbd = chamado.getIdStatus();

/*  aqui testar qdo tiver id            Pessoa pessoa = (Pessoa) session.getAttribute("cache");
              session.setAttribute("cache", pessoa.getNome());

              long idPessoa= pessoa.getId();
              Pessoa pessoaDados = this.pessoaRepository.getReferenceById(idPessoa);
 */


//         Pessoa pessoa1 = this.findIDPessoa((int) idPessoa);

        // aqui switch (status.intValue()) {
        switch (status) {
            case "em-analise":
                statusmodified = this.statusRepository.findById(1L).orElse(null);
                break;
            case "aguardando":
                statusmodified = this.statusRepository.findById(2L).orElse(null);
                break;
            case "em-atendimento":
                statusmodified = this.statusRepository.findById(3L).orElse(null);
                break;
            case "outro-setor":
                statusmodified = this.statusRepository.findById(4L).orElse(null);
                break;
            case "finalizado":
                statusmodified = this.statusRepository.findById(5L).orElse(null);
                break;
            default:
                return "redirect:usuario.novo.chamado";
        }

        if (statusmodified != null || prioridademodified != null) {
            chamado.setIdStatus(statusmodified);
            chamado.setIdPrioridade(prioridademodified);
//  aqui testar qdo tiver id                  chamado.setIdTecnico(pessoa);
            chamadoRepository.save(chamado);

// aqui testar qdo tiver id                  session.setAttribute("cache", pessoa.getNome());
        }
        else{
            chamado.setIdStatus(statusbd);
            chamado.setIdPrioridade(prioridadeBd);
// aqui testar qdo tiver id                  session.setAttribute("cache", pessoa.getNome());
        }

        return "redirect:usuario.novo.chamado"; // + pessoa.getId();
    }


/*
    @PostMapping("/usuario/novo/chamado")
    public String criarNovoChamado(@ModelAttribute ChamadoDTO novoChamado, Model model, HttpSession session) {
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        novoChamado.setId_usuario(userOn);
        novoChamado.setDt_abertura(LocalDate.now());
        chamadoServiceFront.save(novoChamado);
        return "redirect:/tela-usuario";
    }*/


    @GetMapping("/usuario/chamados/{id_usuario}")
    public String findById_Usuario(@PathVariable("id_usuario") Long id_usuario, Model model, HttpSession session) {
        List<ChamadoDTO> chamadoDTO = (List<ChamadoDTO>) chamadoServiceFront.findById_Usuario(id_usuario).getBody();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormadata = dtAbertura.format(formatter);
            dataFormatada.add(dtFormadata);

            LocalDate dtFim = chamado.getDt_fim();
            if (dtFim != null) {
                String dtFimFormatada = dtFim.format(formatter);
                dataFimFormatada.add(dtFimFormatada);
            } else {
                dataFimFormatada.add("");
            }

        }

        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        String nomeUsuario = userOn.getNome();
        model.addAttribute("userOn", userOn);

        model.addAttribute("dtFormatada", dataFormatada);
        model.addAttribute("dtFimFormatada", dataFimFormatada);

        return "usuario.chamados";
    }

    @PostMapping("/usuario/chamado/{id}/excluir")
    public String deleteById(@PathVariable("id") Long id, Model model, HttpSession session) {
        chamadoServiceFront.deleteById(id);
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        Long id_usuario = userOn.getId();
        return "redirect:/usuario/chamados/" + id_usuario;
    }
}





