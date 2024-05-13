package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller

public class TecnicoController {
    @Autowired
    private ChamadoService chamadoService;

    @GetMapping ("/tecnico")
    public String indexUsuario(Model model){
        List<ChamadoDTO> chamadoDTO =  chamadoService.findAllChamados();                                      //this.chamadoService.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);
        return "index.tecnico";//"index.tecnico";
    }

    @GetMapping ("tecnico/chamados")
    public String findAllChamados(Model model){
        List<ChamadoDTO> chamadoDTO =  chamadoService.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
            dataFormatada.add(dtFormatada);
        }
        model.addAttribute("dtFormatada", dataFormatada);
        return "usuario.chamados"; // PRECISA DO ID DO TECNICO PARA FILTRAR CHAMADOS DELE
    }
    // TECNICO NÃO EXCLUIRÁ NEM CRIARÁ NOVO CHAMADO


    @GetMapping ("/usuario/chamado/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        ChamadoDTO chamadoDTO = chamadoService.findById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dtFormatada = chamadoDTO.getDt_abertura().format(formatter);
        model.addAttribute("chamados", chamadoDTO);
        model.addAttribute("dtFormatada", dtFormatada);
        return "usuario.chamados";
    }

    @PostMapping("/usuario/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute("chamadoDTO") ChamadoDTO chamadoDTO){
        chamadoService.updateById(id, chamadoDTO);
        return "usuario/chamados"; //pra voltar pra tela de chamados - VER SE SERÁ MES TELA USUARIO
    }

}
