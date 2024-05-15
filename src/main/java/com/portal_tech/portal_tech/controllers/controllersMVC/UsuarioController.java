package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.serviceFront.ChamadoServiceFront;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("/usuario/novo/chamado")
    public String criarNovoChamado(@ModelAttribute ChamadoDTO novoChamado, Model model, HttpSession session) {
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        novoChamado.setId_usuario(userOn);
        novoChamado.setDt_abertura(LocalDate.now());
        chamadoServiceFront.save(novoChamado);
        return "redirect:/tela-usuario";
    }


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





