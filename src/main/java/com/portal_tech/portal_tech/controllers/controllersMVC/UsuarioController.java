package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.models.dtos.PrioridadeDTO;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
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
        List<Chamado> chamados = chamadoRepository.findById_Usuario(userOn.getId());

        List<Prioridade> listPrioridades = prioridadeRepository.findAll();


        List<PrioridadeDTO> listPrioridadesDTO = new ArrayList<>();
        for(Prioridade prioridade : listPrioridades){
            listPrioridadesDTO.add(new PrioridadeDTO(prioridade));

        }


        model.addAttribute("listPrioridadesDTO", listPrioridadesDTO);

        PrioridadeDTO prioridadeDTO = new PrioridadeDTO();


        model.addAttribute("userOn", userOn);
        return "usuario.novo.chamado";
    }

    public Chamado buscaChamado (Long id){
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        return optionalChamado.orElse(null);
    }

    @PostMapping("/usuario/novo/chamado")
    public String chamados ( @RequestParam("prioridade") int prioridade,
                             HttpSession session, @RequestParam String descricao){

        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        long idUsuario = userOn.getId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtAbertura = LocalDate.now();
        String dtFormatada = dtAbertura.format(formatter);
        LocalDate date = LocalDate.parse(dtFormatada, formatter);
        Chamado chamado = new Chamado();
        chamado.setDescricao(descricao);      chamado.setDt_abertura(date);


        Prioridade prioridade1 = new Prioridade();
        prioridade1.setId((long) prioridade);
        chamado.setIdPrioridade(prioridade1);

        Pessoa pessoa  = new Pessoa();
        pessoa.setId(idUsuario);
        chamado.setIdUsuario(pessoa);


        Chamado chamado1 = this.chamadoRepository.save(chamado);
        System.out.println(chamado1);


                return "redirect:/tela-usuario";
        }



    @GetMapping("/usuario/chamados/{id_usuario}")
    public String findById_Usuario(@PathVariable("id_usuario") Long id_usuario, Model model, HttpSession session) {
        List<Chamado> chamados = this.chamadoRepository.findById_Usuario(id_usuario);
        if(chamados.isEmpty()) {
            Pessoa userOn = (Pessoa) session.getAttribute("cache");
            String nomeUsuario = userOn.getNome();
            model.addAttribute("userOn", userOn);
            return "usuario.chamados";
        }else {
            List<ChamadoDTO> chamadoDTO = new ArrayList<>();
            for (Chamado chamado : chamados) {
                chamadoDTO.add(new ChamadoDTO(chamado));
            }
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
    }

    @PostMapping("/usuario/chamado/{id}/excluir")
    public String deleteById(@PathVariable("id") Long id, Model model, HttpSession session) {
        chamadoServiceFront.deleteById(id);
        Pessoa userOn = (Pessoa) session.getAttribute("cache");
        Long id_usuario = userOn.getId();
        return "redirect:/usuario/chamados/" + id_usuario;
    }
}





