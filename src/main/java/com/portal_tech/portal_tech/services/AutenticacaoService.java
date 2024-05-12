package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AutenticacaoService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private SetorRepository setorRepository;

    public String cadastrar(Model model, HttpServletRequest request,  String nome,
                             String telefone, String email,String senha) {

        List<Tipo> listOfTypes = this.tipoRepository.findAll();

        List<Setor> listOfSetor = this.setorRepository.findAll();


        model.addAttribute("optionTipo", listOfTypes);

        model.addAttribute("radioSetorOptions", listOfSetor );


        String tipoSelected = request.getParameter("tipo");

        Pessoa pessoa = this.pessoaRepository.findEmail(email);


        Pessoa pessoa = getPessoa(nome, telefone, email, senha, tipoSelected);
        this.pessoaRepository.save(pessoa);
        return "redirect:/login";
}

    private static Pessoa getPessoa(String nome, String telefone, String email, String senha, String tipoSelected) {
        Pessoa pessoa = new Pessoa();
        Tipo tipo = new Tipo();
        tipo.setNome(tipoSelected);
        pessoa.setTipo(tipo);

        Setor setor = new Setor();
        setor.setNome("Marketing");

        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);
        return pessoa;
    }


    public String loginSalvar(Model model, HttpServletRequest request, HttpServletResponse response,  String email, Pessoa pessoaParam,String senha) {
        Pessoa pessoa = pessoaRepository.verifyLogin(email, senha);//inf vindas do banco, valida se o email e a senha existem no banco

//        Pessoa pessoa = pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());//inf vindas do banco, valida se o email e a senha existem no banco
        long pessoaID = pessoa.getId();
        String pessoaName = pessoa.getNome();
        long pessoaTipoID = (long) pessoa.getTipo().getId();//tenho o id da pessoa

        String tipoPessoa = getTipoName(pessoaTipoID);
        
        // cria uma sessão e define o usuário como logado, armazenando as informações do usuário logado em cache
        createSession(request, pessoa);

        if (pessoa != null && tipoPessoa.equals("Usuário")) { //o usuário esta cadastrado no banco
            return "redirect:/index/usuario/" + pessoaID + pessoaName;
        } else if(pessoa != null && tipoPessoa.equals("Técnico")) {
            return "redirect:/index/tecnico/" + pessoaID + pessoaName;

        } else if(pessoa != null && equals("Administrador")){
            return "redirect:/index/tecnico/" + pessoaID + pessoaName;
        }else{
            model.addAttribute("erro", "Usuário ou senhas inválidos");//mensagem de erro na tela de login
            return "/login";
        }
    }

    private static void createSession(HttpServletRequest request, Pessoa pessoa) {
        HttpSession session = request.getSession();
        session.setAttribute("cache", pessoa);
    }

    private String getTipoName(long pessoaTipoID) {
        List<Tipo> listTipo = this.tipoRepository.findAll();
        long tipoPessoaID = 0;
        String tipoPessoa = "";
        for(Tipo tipo : listTipo){
            tipoPessoaID = tipo.getId();
            if(tipoPessoaID == pessoaTipoID){
                tipoPessoa = tipo.getNome();
            }

        }
        return tipoPessoa;
    }
}