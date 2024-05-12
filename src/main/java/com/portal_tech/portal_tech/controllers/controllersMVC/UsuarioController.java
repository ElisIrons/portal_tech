package com.portal_tech.portal_tech.controllers.controllersMVC;


import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


import java.util.List;


@Controller
public class UsuarioController {

    @Autowired
    private ChamadoService chamadoService;


    @GetMapping("/usuario/novo/chamado")
    public String novoChamado(){
        return "usuario.novo.chamado"; // Este é o nome do arquivo HTML para a rota de novo chamado
    }


    @GetMapping("/usuario/chamados")
    public String chamadosFeitos(Model model) {
        List<ChamadoDTO> chamados = chamadoService.findAllChamados();
        model.addAttribute("chamados", chamados);
        return "usuario.chamados";
    }


    @GetMapping("/tela-usuario")
    public String indexUsuario() {
        return "index.usuario";
    }

}