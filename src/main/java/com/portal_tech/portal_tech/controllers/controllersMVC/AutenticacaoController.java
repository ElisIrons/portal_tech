package com.portal_tech.portal_tech.controllers.controllersMVC;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSalvar(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String email, Pessoa pessoaParam, @RequestParam("senha") String senha) {

        Pessoa pessoa = this.pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());//inf vindas do banco
        if(pessoa != null){ //o usuário esta cadastrado no banco

        }

        if(pessoa.getTipo().getNome().equals("Usuário")){
            return "redirect:/index.usuario";
        }else if(pessoa.getTipo().getNome().equals("Técnico")){
            return "redirect:/index.usuario";
        } else if (pessoa.getTipo().getNome().equals("Administrador")) {
            return "redirect:/usuario/admin";
        }else {
            model.addAttribute("erro", "Usuário ou senhas inválidos");//mensagem de erro na tela de login
            return "/login";
        }
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastrar(@RequestParam("senha") String senha){
        System.out.println("senha   " + senha);
        return "/cadastro";
    }

//    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
//    public String cadastro() {
//        return "cadastro";
//    }
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastrar() {

//        System.out.println("nome: " + nome + "telefone: " + telefone + "email: " + email + "senha " + senha);
//       salvar essas informações no banco e depos retornar. Caso a pessoa for usuaria,,
//       List<String> radioOptions = Arrays.asList("Usuário", "Técnico", "Administrador");
//       model.addAttribute("raadioOptions", radioOptions);
//        String personSelected = request.getParameter("tipo");
//        String radio1 = radio;// recebendo a opção que a pessoa selecionou
//        String setorSelected = request.getParameter("setor");
//        String nome = request.getParameter("nome");
//        String telefone = request.getParameter("telefone");
//        String email = request.getParameter("email");
//        String senha = request.getParameter("senha");
//        System.out.println(" A opção selecionada do radio foi: " + personSelected + "A opção do select setor com RequestParam : " + radio1 + " O setor: " + setorSelected
//                + " nome: " + nome + "telefone " + telefone + "email: " + email + "senha: " + senha);
//        System.out.println("personSelected: " + personSelected + "radio " + radio1);
        return "/cadastro";

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











