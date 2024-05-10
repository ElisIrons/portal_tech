package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import com.portal_tech.portal_tech.services.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AutenticacaoController {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private AutenticacaoService autenticacaoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastrar() {
        return "/cadastro";
    }

//Falta implementar lógica para redirecionar os usuarios para pags certas. Se tiver, add as exceções
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSalvar(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email, Pessoa pessoaParam, @RequestParam("senha") String senha) {


        Pessoa pessoa = this.pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());//inf vindas do banco
        if (pessoa != null) { //o usuário esta cadastrado no banco
            return "redirect:/index/usuario";
        } else {
            model.addAttribute("erro", "Usuário ou senhas inválidos");//mensagem de erro na tela de login
            return "/login";
        }
    }



//Falta arrumar este código para deixar mais enxuto nos parametros
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastrar(Model model, HttpServletRequest request, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone, @RequestParam("email") String email, @RequestParam("senha") String senha) {

     return this.autenticacaoService.cadastrar(model, request,  nome,  telefone,email, senha);

    }

    //fazer um método para verificar se os emails, já existem durante o cadastro
}











