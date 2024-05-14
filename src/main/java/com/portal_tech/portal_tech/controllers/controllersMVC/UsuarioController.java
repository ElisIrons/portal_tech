package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import com.portal_tech.portal_tech.services.ChamadoServiceFront;
import com.portal_tech.portal_tech.swaggerDoc.UsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private ChamadoServiceFront chamadoServiceFront;


    // Método para criar um novo chamado quando uma solicitação POST é feita para /usuario/novo/chamado
    @PostMapping("/usuario/novo/chamado")
    public String criarNovoChamado(@ModelAttribute ChamadoDTO novoChamado) {
        chamadoServiceFront.save(novoChamado);
        return "redirect:/usuario/chamados"; // Redireciona para a página de chamados do usuário
    }


    // Método para visualizar todos os chamados quando uma solicitação GET é feita para /usuario/chamados
    @GetMapping("/usuario/chamados/{id}")
    public String visualizarChamados(Model model) {
        List<ChamadoDTO> chamadoDTO = chamadoServiceFront.findAllChamados().getBody();
        model.addAttribute("chamados", chamadoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> dataFormatada = new ArrayList<>();
        for (ChamadoDTO chamado : chamadoDTO) {
            LocalDate dtAbertura = chamado.getDt_abertura();
            String dtFormatada = dtAbertura.format(formatter);
            dataFormatada.add(dtFormatada);
        }
        model.addAttribute("dtFormatada", dataFormatada);
        return "usuario.chamados";
    }


    // Método para excluir um chamado quando uma solicitação POST é feita para /usuario/chamado/{id}/excluir
    @DeleteMapping("/usuario/chamado/{id}/excluir")
    public String excluirChamado(@PathVariable("id") Long id) {
        chamadoServiceFront.deleteById(id);
        return "/usuario/chamados";
    }


    // Método para acessar a tela de novo chamado quando uma solicitação GET é feita para /usuario/novo/chamado
    @GetMapping("/usuario/novo/chamado")
    public String novaTelaChamado() {
        return "usuario.novo.chamado";
    }

    @GetMapping("/tela-usuario")
    public String exibirTelaUsuario() {
        return "index.usuario";
    }
}
