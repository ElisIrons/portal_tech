package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoServiceFront;
import com.portal_tech.portal_tech.swaggerDoc.TecnicoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller

public class TecnicoController { //implements TecnicoControllerOpenApi {

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;

    @GetMapping ("/tecnico")
    public String findAllChamados(Model model){
        List<ChamadoDTO> chamadoDTO =  chamadoServiceFront.findAllChamados().getBody();                                      //this.chamadoService.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
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

        return "index.tecnico";
    }
    /*@GetMapping ("/tecnico")
    public String indexUsuario(Model model){
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();                                      //this.chamadoService.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
            dataFormatada.add(dtFormatada);
        }
        model.addAttribute("dtFormatada", dataFormatada);

        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtFim = chamado.getDt_fim();
            if (dtFim != null) {
                String dtFimFormatada = dtFim.format(formatter);
                dataFimFormatada.add(dtFimFormatada);
            }
            else {
                dataFimFormatada.add("");
            }
        }
        model.addAttribute("dtFimFormatada", dataFimFormatada);

        return "index.tecnico";
    }*/
    // TECNICO NÃO EXCLUIRÁ NEM CRIARÁ NOVO CHAMADO

    @GetMapping ("/index/tecnico/{id}")
    public String findById_Tecnico(@PathVariable("id") Long id_tecnico, Model model){
        List<ChamadoDTO> chamadoDTO =  chamadoServiceFront.findById_Tecnico(id_tecnico).getBody();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
            dataFormatada.add(dtFormatada);
        }
        model.addAttribute("dtFormatada", dataFormatada);

        List<String> dataFimFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO){
            LocalDate dtFim = chamado.getDt_fim();
            if (dtFim != null) {
                String dtFimFormatada = dtFim.format(formatter);
                dataFimFormatada.add(dtFimFormatada);
            }
            else {
                dataFimFormatada.add("");
            }
        }
        model.addAttribute("dtFimFormatada", dataFimFormatada);

        return "index.tecnico";
    }




/*    @GetMapping ("usuario.chamados")
    @GetMapping ("tecnico/chamados")
    public String findAllChamados(Model model){
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
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
    }*/
    // TECNICO NÃO EXCLUIRÁ NEM CRIARÁ NOVO CHAMADO


    @GetMapping ("/usuario/chamado/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        ChamadoDTO chamadoDTO = chamadoServiceFront.findById(id).getBody();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dtFormatada = null, dtFimFormatada = null;
        if (chamadoDTO.getDt_abertura() != null) {
            dtFormatada = chamadoDTO.getDt_abertura().format(formatter);
        }
        if (chamadoDTO.getDt_fim() != null) {
            dtFimFormatada = chamadoDTO.getDt_fim().format(formatter);
        }
        model.addAttribute("chamados", chamadoDTO);
        model.addAttribute("dtFormatada", dtFormatada);
        model.addAttribute("dtFimFormatada", dtFimFormatada);
        return "usuario.chamados";
    }

    @PostMapping("/{id}")
    public String updateById(@PathVariable Long id, @ModelAttribute("chamadoDTO") ChamadoDTO chamadoDTO){
        chamadoServiceFront.updateById(id, chamadoDTO);
        return "usuario/chamados"; //pra voltar pra tela de chamados - VER SE SERÁ MES TELA USUARIO
    }

}
