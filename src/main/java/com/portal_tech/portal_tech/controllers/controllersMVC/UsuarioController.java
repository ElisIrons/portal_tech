package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.services.AutenticacaoService;
import com.portal_tech.portal_tech.services.ChamadoServiceFront;
import com.portal_tech.portal_tech.swaggerDoc.UsuarioControllerOpenApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;



    // Método para criar um novo chamado quando uma solicitação POST é feita para /usuario/novo/chamado
    @PostMapping("/usuario/novo/chamado")
    public String novaTelaChamado(@ModelAttribute ChamadoDTO novoChamado, Model model, HttpSession session) {
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
        model.addAttribute("chamados", chamadoDTO);
        Pessoa userOn = (Pessoa) session.getAttribute("cache");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataInicioFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtInicio = chamado.getDt_inicio();
            String dtFormatada = "";
            if (dtInicio != null) {
                dtFormatada = dtInicio.format(formatter);
            }

            LocalDate dtFim = chamado.getDt_fim();
            if (dtFim != null) {
                String dtFimFormatada = dtFim.format(formatter);
                dataFimFormatada.add(dtFimFormatada);
            }
            else {
                dataFimFormatada.add("");
            }


        }
        novoChamado.setId_usuario(userOn);
        model.addAttribute("dtFormatada", dataInicioFormatada);
        model.addAttribute("dtFimFormatada", dataFimFormatada);
        novoChamado.setDt_abertura(LocalDate.now());
        chamadoServiceFront.save(novoChamado);
        return "redirect:/tela-usuario";
    }




//     Método para visualizar todos os chamados quando uma solicitação GET é feita para /usuario/chamados
    @GetMapping("/usuario/chamados")
    public String visualizarChamados(Model model) {
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtInicio = chamado.getDt_inicio();
            String dtFormatada = dtInicio.format(formatter);
            dataFormatada.add(dtFormatada);

        LocalDate dtFim = chamado.getDt_fim();
        if (dtFim != null) {
            String dtFimFormatada = dtFim.format(formatter);
            dataFimFormatada.add(dtFimFormatada);
        }
        else {
            dataFimFormatada.add("");
        }
    }
        model.addAttribute("dtFormatada", dataFormatada);
        model.addAttribute("dtFimFormatada", dataFimFormatada);
        return "usuario.chamados";
    }



//    // Método para excluir um chamado quando uma solicitação POST é feita para /usuario/chamado/{id}/excluir
//    @DeleteMapping("/usuario/chamado/{id}/excluir")
//    public String excluirChamado(@PathVariable("id") Long id) {
//        chamadoServiceFront.deleteById(id);
//        return "/usuario/chamados";
//    }


    // Método para acessar a tela de novo chamado quando uma solicitação GET é feita para /usuario/novo/chamado
    @GetMapping("/usuario/novo/chamado")
    public String novaTelaChamado(Model model, HttpSession session) {

        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtInicio = chamado.getDt_inicio();
            if (dtInicio != null) {
                String dtFormatada = dtInicio.format(formatter);
                dataFormatada.add(dtFormatada);
            } else {
                dataFormatada.add(""); // Adiciona uma string vazia caso dtInicio seja nulo
            }

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
        return "usuario.novo.chamado";
    }



    @GetMapping("/tela-usuario")
    public String exibirTelaUsuario(Model model, HttpSession session) {
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        String nomeUsuario = userOn.getNome();
        model.addAttribute("userOn", userOn);
        return "tela.usuario";
    }
}
