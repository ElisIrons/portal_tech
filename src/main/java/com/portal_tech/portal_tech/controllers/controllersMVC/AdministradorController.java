package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Status;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.PrioridadeRepository;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.repositores.StatusRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdministradorController {

    private final ChamadoRepository chamadoRepository;
    private final PessoaRepository pessoaRepository;
    private final PrioridadeRepository prioridadeRepository;
    private final SetorRepository setorRepository;
    private final StatusRepository statusRepository;
    private final TipoRepository tipoRepository;

    @Autowired
    public AdministradorController
        (ChamadoRepository chamadoRepository,
         PessoaRepository pessoaRepository,
         PrioridadeRepository prioridadeRepository,
         SetorRepository setorRepository,
         StatusRepository statusRepository,
         TipoRepository tipoRepository) {
        this.chamadoRepository = chamadoRepository;
        this.pessoaRepository = pessoaRepository;
        this.prioridadeRepository = prioridadeRepository;
        this.setorRepository = setorRepository;
        this.statusRepository = statusRepository;
        this.tipoRepository = tipoRepository;
    }

    @GetMapping("/adminpanel")
    public String adminPanel(Model model) {
        List<Chamado> chamados = chamadoRepository.findAll();
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<Prioridade> prioridades = prioridadeRepository.findAll();
        List<Setor> setores = setorRepository.findAll();
        List<Status> status = statusRepository.findAll();
        List<Tipo> tipos = tipoRepository.findAll();

        model.addAttribute("chamados", chamados);
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("prioridades", prioridades);
        model.addAttribute("setores", setores);
        model.addAttribute("status", status);
        model.addAttribute("tipos", tipos);

        return "adminpanel";

            }
}
