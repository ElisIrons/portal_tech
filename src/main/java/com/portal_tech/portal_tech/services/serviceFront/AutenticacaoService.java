package com.portal_tech.portal_tech.services.serviceFront;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
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


        model.addAttribute("optionTipo", listOfTypes);


        String tipoSelected = request.getParameter("tipo");

        String setorSelected = request.getParameter("setor");

        Pessoa pessoaEmail = this.pessoaRepository.findEmail(email);

        if (pessoaEmail == null) {
            Pessoa pessoa = getPessoa(nome, telefone, email, senha, tipoSelected, setorSelected);
            this.pessoaRepository.save(pessoa);
            return "redirect:/login";
        }else {
            model.addAttribute("erro", "Email já cadastrado!");
            return "/cadastro";
        }



}

    private static Pessoa getPessoa(String nome, String telefone, String email, String senha, String tipoSelected, String setorSelected) {
        Pessoa pessoa = new Pessoa();
        Tipo tipo = new Tipo();


        tipo.setNome(tipoSelected);


        Setor setor = new Setor();
        if(setorSelected == null) {
           setor.setNome(" ");

        }else {
            setor.setNome(setorSelected);
        }
        pessoa.setSetor(setor);
//        pessoa.setId(setor.getId());

//        Tipo tipo = new Tipo((long) 0,tipoSelected );
        pessoa.setTipo(tipo);
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);
        return pessoa;
    }

    public String loginAuth(Model model, HttpServletRequest request, HttpServletResponse response,  String email, Pessoa pessoaParam,String senha) {


        Pessoa pessoa = pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());//inf vindas do banco, valida se o email e a senha existem no banco

        String tipoPessoa = "";
        if (pessoa == null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");//mensagem de erro na tela de login
            return "/login";

        }else {
            long pessoaTipoID = (long) pessoa.getTipo().getId();//tenho o id da pessoa
            List<Tipo> listTipo = this.tipoRepository.findAll();
            long tipoPessoaID = 0;
            for (Tipo tipo : listTipo) {
                tipoPessoaID = tipo.getId();
                if (tipoPessoaID == pessoaTipoID) {
                    tipoPessoa = tipo.getNome();
                }
            }

// cria uma sessão e define o usuário como logado, armazenando as informações do usuário logado em cache
            HttpSession session = request.getSession();
            session.setAttribute("cache", pessoa);

            if (pessoa != null && tipoPessoa.equals("Usuário")) {
                long pessoaID = pessoa.getId();
                String pessoaName = pessoa.getNome();//o usuário esta cadastrado no banco
                return "redirect:/tela-usuario";
            } else if (pessoa != null && tipoPessoa.equals("Técnico")) {
                long pessoaID = pessoa.getId();
                String pessoaName = pessoa.getNome();
                return "redirect:/tecnico/" + pessoaID;

            } else if (pessoa != null && tipoPessoa.equals("Administrador")) {
                long pessoaID = pessoa.getId();
                String pessoaName = pessoa.getNome();
                return "redirect:/adminpainel/" + pessoaID;
            } else {
                model.addAttribute("erro", "Usuário ou senha inválidos");//mensagem de erro na tela de login
                return "/login";
            }
        }
        }
}