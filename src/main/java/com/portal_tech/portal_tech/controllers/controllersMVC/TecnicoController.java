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
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @GetMapping("/tecnico")
    public String findAllChamados(Model model, HttpSession session) {
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();                                      //this.chamadoService.findAllChamados();
        model.addAttribute("chamados", chamadoDTO);

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

    /*    Pessoa userOn = (Pessoa) session.getAttribute("cache");
        String nomeUsuario = userOn.getNome();
        model.addAttribute("userOn", userOn);*/
        //return "index.tecnico"; --agora
        return "tela.tecnico";
        //return "/index.tecnico";

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

        @GetMapping("/tecnico/{id}")
        public String findById_Tecnico (@PathVariable("id") Long id_tecnico, Model model, HttpSession session){
            List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findById_Tecnico(id_tecnico).getBody();
            model.addAttribute("chamados", chamadoDTO);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List<String> dataFormatada = new ArrayList<>();
            for (ChamadoDTO chamado : chamadoDTO) {
                LocalDate dtAbertura = chamado.getDt_abertura();
                String dtFormatada = dtAbertura.format(formatter);
                dataFormatada.add(dtFormatada);
            }
            model.addAttribute("dtFormatada", dataFormatada);

            List<String> dataFimFormatada = new ArrayList<>();
            for (ChamadoDTO chamado : chamadoDTO) {
                LocalDate dtFim = chamado.getDt_fim();
                if (dtFim != null) {
                    String dtFimFormatada = dtFim.format(formatter);
                    dataFimFormatada.add(dtFimFormatada);
                } else {
                    dataFimFormatada.add("");
                }
            }
            model.addAttribute("dtFimFormatada", dataFimFormatada);

            Pessoa userOn = (Pessoa) session.getAttribute("cache");
            String nomeUsuario = userOn.getNome();
            model.addAttribute("userOn", nomeUsuario);

            return "tela.tecnico";
        }

        @PostMapping("/tecnico")
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
                    return "redirect:/tecnico";
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
                      return "redirect:/tecnico";
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

            return "redirect:/tecnico"; // + pessoa.getId();
        }

        public Chamado FindIDChamado ( int id){
            Optional<Chamado> optionalChamado = chamadoRepository.findById((long) id);
            return optionalChamado.orElse(null);
        }

    public Chamado buscaChamado (Long id){
        Optional<Chamado> optionalChamado = chamadoRepository.findById(id);
        return optionalChamado.orElse(null);
    }

        public Pessoa findIDPessoa ( int idPessoa){
            Optional<Pessoa> pessoa1 = this.pessoaRepository.findById(Long.valueOf(idPessoa));
            return pessoa1.orElse(null);
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


        @GetMapping("/usuario/chamado/{id}")
        public String findById (@PathVariable("id") Long id, Model model){
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

        @PostMapping("/tecnico/{id_tecnico}) //     /{id_chamado}")
        public String updateById (@PathVariable("id_tecnico") Long id_tecnico, @PathVariable("id_chamado") Long
        id_chamado, @ModelAttribute("chamadoDTO") ChamadoDTO chamadoDTO){
            chamadoServiceFront.updateById(chamadoDTO.getId(), chamadoDTO);
            return "redirect:/tecnico/" + id_tecnico; //pra voltar pra tela de chamados
        }

}
