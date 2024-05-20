package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;

import com.portal_tech.portal_tech.services.serviceFront.AutenticacaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AutenticacaoController {

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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email, Pessoa pessoaParam,
                        @RequestParam("senha") String senha) {
        return this.autenticacaoService.loginAuth(model, request, response, email, pessoaParam, senha);
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String register(Model model, HttpServletRequest request, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone,
                           @RequestParam("email") String email, @RequestParam("senha") String senha) {
        return autenticacaoService.register(model, request, nome, telefone, email, senha);
    }
}
















