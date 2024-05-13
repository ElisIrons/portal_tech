package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Teste;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.services.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private SetorRepository setorRepository;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastrar() {
        return "/cadastro";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email, Pessoa pessoaParam, @RequestParam("senha") String senha) {
        return this.autenticacaoService.loginAuth(model, request, response, email, pessoaParam, senha);
    }


    //Falta arrumar este código para deixar mais enxuto nos parametros
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastrar(Model model, HttpServletRequest request, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone, @RequestParam("email") String email, @RequestParam("senha") String senha) {

        return autenticacaoService.cadastrar(model, request, nome, telefone, email, senha);

    }


        @GetMapping("/teste")
        public String index(Model model) {
            List<String> setores = Arrays.asList("Administrativo", "Marketing", "Recursos Humanos","Suporte");
            List<Setor> listOfSetor = this.setorRepository.findAll();
            model.addAttribute("setores", setores);
//            model.addAttribute( "selecionado", new Teste(2L, "Brasil") );

            return "/teste";
        }

    @PostMapping("/teste")
    public String indexPost(Model model, @RequestParam("setor") String nome) {
        System.out.println(nome);

        return "/teste";
    }

    }
















