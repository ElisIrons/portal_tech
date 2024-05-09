package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutenticacaoController {
@Autowired
private PessoaRepository pessoaRepository;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }


    @RequestMapping(value = "/index/usuario", method = RequestMethod.GET)
    public String indexUsuario() {
        return "/index.usuario";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, @RequestParam("email") String email, Pessoa pessoaParam, @RequestParam("senha") String senha) {
//salvar essas informações no banco e depos retornar. Caso a pessoa for usuaria,,
//        List<String> radioOptions = Arrays.asList("Usuário", "Técnico", "Administrador");
//        model.addAttribute("raadioOptions", radioOptions);
//        String personSelected = request.getParameter("person"); // recebendo a opção que a pessoa selecionou
        Pessoa pessoa = this.pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());
        if (pessoa != null) {
            return "/index/usuario";
        } else {
            model.addAttribute("erro", "Usuário ou senhas inválidos");
            return "/login";
        }
    }

    public String cadastrar(){

    }

    //identificar o tipo de usuário cadastrado para redirecioná-lo para tela certa

        //Encontrar os usuários pelo email
//        comparar os tipos desse usuário com o resultado do radio-button e redirecionar p/pags
//chamar o metodo de verificação aqui


//        return "redirect:/index.usuario" ;
    }












