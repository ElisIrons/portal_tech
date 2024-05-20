package com.portal_tech.portal_tech.services.serviceFront;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
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


    public String register(Model model, HttpServletRequest request, String nome,
                           String telefone, String email, String senha) {

        List<Tipo> listOfTypes = this.tipoRepository.findAll();

        model.addAttribute("optionTipo", listOfTypes);

        String tipoSelected = request.getParameter("tipo");

        String setorSelected = request.getParameter("setor");

        Pessoa pessoaEmail = this.pessoaRepository.findEmail(email);

        if (pessoaEmail == null) {
            Pessoa pessoa = getPerson(nome, telefone, email, senha, tipoSelected, setorSelected);
            this.pessoaRepository.save(pessoa);
            return "redirect:/login";
        } else {
            model.addAttribute("erro", "Email já cadastrado!");
            return "/cadastro";
        }

    }


    public String loginAuth(Model model, HttpServletRequest request, HttpServletResponse response, String email, Pessoa pessoaParam, String senha) {

        Pessoa pessoa = pessoaRepository.verifyLogin(pessoaParam.getEmail(), pessoaParam.getSenha());

        String tipoPessoa = "";
        if (pessoa == null) {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "/login";

        } else {
            tipoPessoa = findTypeOfPersonLogged(pessoa, tipoPessoa);

// cria uma sessão e define o usuário como logado, armazenando as informações do usuário logado em cache
            createSession(request, pessoa);

            if (pessoa != null && tipoPessoa.equals("Usuário")) {

                return "redirect:/tela-usuario";

            } else if (pessoa != null && tipoPessoa.equals("Técnico")) {
                long pessoaID = pessoa.getId();

                return "redirect:/tecnico/" + pessoaID;

            } else if (pessoa != null && tipoPessoa.equals("Administrador")) {

                return "redirect:/adminpainel";
            } else {
                model.addAttribute("erro", "Usuário ou senha inválidos");//mensagem de erro na tela de login
                return "/login";
            }
        }
    }


    private static Pessoa getPerson(String nome, String telefone, String email, String senha, String tipoSelected, String setorSelected) {
        Pessoa pessoa = new Pessoa();
        Tipo tipo = new Tipo();

        tipo.setNome(tipoSelected);

        Setor setor = new Setor();
        if (setorSelected == null) {
            setor.setNome(" ");

        } else {
            setor.setNome(setorSelected);
        }
        pessoa.setSetor(setor);
        pessoa.setTipo(tipo);
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);
        return pessoa;
    }


    private static void createSession(HttpServletRequest request, Pessoa pessoa) {
        HttpSession session = request.getSession();
        session.setAttribute("cache", pessoa);
    }

    private String findTypeOfPersonLogged(Pessoa pessoa, String tipoPessoa) {
        long pessoaTipoID = pessoa.getTipo().getId();//tenho o id da pessoa
        List<Tipo> listTipo = this.tipoRepository.findAll();
        long tipoID = 0;
        for (Tipo tipo : listTipo) {
            tipoID = tipo.getId();
            if (tipoID == pessoaTipoID) {
                tipoPessoa = tipo.getNome();
            }
        }
        return tipoPessoa;
    }
}