package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.services.ChamadoServiceFront;
import com.portal_tech.portal_tech.swaggerDoc.TecnicoControllerOpenApi;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class TecnicoController { //implements TecnicoControllerOpenApi {

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private StatusRepository statusRepository;
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

        return "/index.tecnico";
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
    @PostMapping("/tecnico")
    public String chamados(@RequestParam("status") long status, @RequestParam int id, HttpSession session, String algo){
//        ModelAndView modelAndView = new ModelAndView("/tecnico");
//        List<ChamadoDTO> chamadoDTO =  chamadoServiceFront.findAllChamados().getBody();
        Chamado chamado = this.FindIDChamado(id);
        Status statusmodified = null;
        Pessoa pessoa =(Pessoa) session.getAttribute("cache");
        session.setAttribute("cache", pessoa.getNome() );



        switch ((int) status){
            case 1:
                statusmodified = this.statusRepository.findById(1L).orElse(null);
                break;
            case  2 :
                statusmodified  =this.statusRepository.findById(2L).orElse(null);
                break;
            case  3:
                statusmodified = this.statusRepository.findById(3L).orElse(null);
                break;
            case  4:
                statusmodified = this.statusRepository.findById(4L).orElse(null);
                break;
            default:
                return "redirect:/tecnico";
        }

        if(statusmodified != null){
            chamado.setIdStatus(statusmodified);
            chamado.setIdTecnico(pessoa);
            chamadoRepository.save(chamado);
        }
//
//           modelAndView.addObject("chamados", new ChamadoDTO() );


        return "redirect:/tecnico";
    }

    public Chamado FindIDChamado(int id) {
        Optional<Chamado> optionalChamado = chamadoRepository.findById((long) id);
        return optionalChamado.orElse(null);
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
