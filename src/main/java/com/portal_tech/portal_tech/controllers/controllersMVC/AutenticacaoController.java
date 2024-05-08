package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.repositores.PessoaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.portal_tech.portal_tech.services.PessoaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class AutenticacaoController {
@Autowired
private PessoaRepository pessoaRepository;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request) {
//salvar essas informações no banco e depos retornar. Caso a pessoa for usuaria,,
        List<String> radioOptions = Arrays.asList("Usuário", "Técnico", "Administrador");
        model.addAttribute("raadioOptions", radioOptions);
        String personSelected = request.getParameter("person"); // recebendo a opção que a pessoa selecionou

        //Encontrar os usuários pelo email
//        comparar os tipos desse usuário com o resultado do radio-button e redirecionar p/pags



        return "redirect:/index.usuario.html" ;
    }




}





