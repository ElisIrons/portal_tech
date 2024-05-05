package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.TiposEnums;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.models.dtos.TipoDTO;
import com.portal_tech.portal_tech.repositores.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<PessoaDTO> save(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.nome());
        pessoa.setEmail(pessoaDTO.email());
        pessoa.setSenha(pessoaDTO.senha());
        pessoa.setTelefone(pessoaDTO.telefone());

        Tipo tipo = new Tipo(pessoaDTO.idTipo(), "USUARIO");
        pessoa.setTipo(tipo);

        Setor setor = new Setor( pessoaDTO.idSetor(), "ADMINISTRATIVO");
        pessoa.setSetor(setor);

        this.pessoaRepository.save(pessoa);
        return new ResponseEntity<>(pessoaDTO, HttpStatus.CREATED);
    }

    public List<Pessoa> findAll() {
        List<Pessoa> listOfAll = this.pessoaRepository.findAll();
//        Pessoa pessoa = new Pessoa();
//        PessoaDTO pessoaDTO = new PessoaDTO((int) pessoa.getId(),pessoa.getNome(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getEmail(), pessoa.getSetor().getId(), Math.toIntExact((Long) pessoa.getTipo().getId()));


//        List<PessoaDTO> ListOfUser = new ArrayList<>();
//        List<PessoaDTO> ListOfTechnicians = new ArrayList<>();
//        List<PessoaDTO> ListOfAdmins = new ArrayList<>();

//        return listOfAll;
        return listOfAll.stream().map(Pessoa :: new).collect(Collectors.toList());
//        return collect;
    }

    public ResponseEntity<PessoaDTO> updateInfById(long id, PessoaDTO pessoaDTO) {
        Optional <Pessoa> pessoa = this.pessoaRepository.findById(id);
        if (pessoa.isEmpty()){
            throw new RuntimeException("Tipo de usuário não encontrado!");
        }
        else {
            Pessoa pessoaNova = new Pessoa();
            pessoaNova.setId(id);
            pessoaNova.setId(id);
            pessoaNova.setNome(pessoaDTO.nome());
            pessoaNova.setEmail(pessoaDTO.email());
            pessoaNova.setTelefone(pessoaDTO.telefone());
            Setor setor = new Setor(pessoa.get().getSetor().getId(), pessoa.get().getSetor().getNome());
            pessoaNova.setSetor(setor);
            pessoaNova.setSenha(pessoaDTO.senha());
            Tipo tipo = new Tipo(pessoa.get().getTipo().getId(), pessoa.get().getTipo().getNome());
            pessoaNova.setTipo(tipo);
            this.pessoaRepository.save(pessoaNova);
            return new ResponseEntity<>(new PessoaDTO((int) pessoaNova.getId(),pessoaNova.getNome(),pessoaNova.getEmail(),pessoaNova.getTelefone(), pessoaNova.getSenha(), pessoaNova.getSetor().getId(), (int) pessoaNova.getTipo().getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }

    }
    public ResponseEntity<String> deleteById(long id) {
        this.pessoaRepository.deleteById(id);
        return new ResponseEntity<>("Os dados foram deletados com sucesso", HttpStatus.OK);
    }

    public ResponseEntity<PessoaDTO> finfInfById(long id) {
      Optional <Pessoa> pessoa = this.pessoaRepository.findById(id);
        if (pessoa.isEmpty()){
            throw new RuntimeException("Tipo de usuário não encontrado!");
        }
        else {
            return new ResponseEntity<>(new PessoaDTO((int) pessoa.get().getId(),pessoa.get().getNome(),pessoa.get().getEmail(),pessoa.get().getTelefone(), pessoa.get().getSenha(), pessoa.get().getSetor().getId(), (int) pessoa.get().getTipo().getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }
    }
}
