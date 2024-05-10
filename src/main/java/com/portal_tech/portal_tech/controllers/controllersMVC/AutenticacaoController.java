package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

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
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSalvar(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email, Pessoa pessoaParam, @RequestParam("senha") String senha) {

        Pessoa pessoa = this.pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());//inf vindas do banco
        if(pessoa != null){ //o usuário esta cadastrado no banco
        return "redirect:/index/usuario";
        }else{
            model.addAttribute("erro", "Usuário ou senhas inválidos");//mensagem de erro na tela de login
            return "/login";
        }
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastrar(){
        return "/cadastro";
    }

//    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
//    public String cadastro() {
//        return "cadastro";
//    }

//    public String cadastrar(@RequestParam("nome") String nome,@RequestParam("telefone") String telefone, @RequestParam("email") String email,@RequestParam("senha") String senha, Model model, HttpServletRequest request) {
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastrar(Model model, HttpServletRequest request){

        String setorSelected = request.getParameter("optionSetor");
        String tipoSelected = request.getParameter("optionTipo");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        System.out.println(" A opção selecionada do radio foi: " + "personSelected" + "  O setor: " + setorSelected
                + " nome1: " + nome + "telefone " + telefone + "email: " + email + "senha: " + senha);
        System.out.println("personSelected: " );

        Pessoa pessoa = new Pessoa();
        Tipo tipo = new Tipo();
        tipo.setNome("Usuario");

        Setor setor = new Setor();
        setor.setNome(setorSelected);

        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);
        this.pessoaRepository.save(pessoa);
        return "/login";

    }
}

    //identificar o tipo de usuário cadastrado para redirecioná-lo para tela certa

        //Encontrar os usuários pelo email
//        comparar os tipos desse usuário com o resultado do radio-button e redirecionar p/pags
//chamar o metodo de verificação aqui


//        return "redirect:/index.usuario" ;
//    }


//usar o método para achar o usuario por id
//comparar o id do usuário para pegar o tipo de usuario cadastrado e redirecionae para as pags


//    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
//    public String cadastrar(@RequestParam("nome") String nome,@RequestParam("telefone") String telefone, @RequestParam("email") String email,@RequestParam("senha") String senha) {
//        System.out.println("nome: " + nome + "telefone: " + telefone + "email: " + email + "senha " + senha);
//    }











