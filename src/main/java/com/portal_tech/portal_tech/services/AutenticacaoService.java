package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
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

//        List<String> radioSetorOptions = Arrays.asList("Administrativo", "Marketing", "Recursos Humanos", "Suporte");
        model.addAttribute("radioSetorOptions", listOfSetor );

        String tipoSelected = request.getParameter("tipo");



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
}