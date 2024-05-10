package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
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


    public String cadastrar(Model model, HttpServletRequest request, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone, @RequestParam("email") String email, @RequestParam("senha") String senha) {

        List<Tipo> listOfTypes = new ArrayList<>();
        listOfTypes = this.tipoRepository.findAll();

        List<String> radioTipoOptions = Arrays.asList("Usuário", "Técnico", "Administrador");
        model.addAttribute("optionTipo", listOfTypes);

        List<String> radioSetorOptions = Arrays.asList("Administrativo", "Marketing", "Recursos Humanos", "Suporte");
        model.addAttribute("radioSetorOptions", radioSetorOptions);


        String tipoSelected = request.getParameter("tipo");

        System.out.println(" A opção selecionada do radio foi: " + "personSelected" + "  O setor: "
                + " nome1: " + nome + "telefone " + telefone + "email: " + email + "senha: " + senha);
        System.out.println("personSelected: ");

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
        this.pessoaRepository.save(pessoa);
        return "/login";
}
}