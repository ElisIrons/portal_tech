package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.dtos.PessoaDTO;

import com.portal_tech.portal_tech.repositores.PessoaRepository;
import com.portal_tech.portal_tech.repositores.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TipoRepository tipoRepository;

    public ResponseEntity<PessoaDTO> register(Map<String, Object> pessoaDTO) {

        String nomeTipo = (String) pessoaDTO.get("nomeTipo");

        String nomeSetor = (String) pessoaDTO.get("nomeSetor");
        int idTipo = (int) pessoaDTO.get("idTipo");
        int idSetor = (int) pessoaDTO.get("idSetor");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome((String) pessoaDTO.get("nome"));
        pessoa.setEmail((String) pessoaDTO.get("email"));
        pessoa.setSenha((String) pessoaDTO.get("senha"));
        pessoa.setTelefone((String) pessoaDTO.get("telefone"));

        Tipo tipo = new Tipo((long) idTipo,nomeTipo );
        pessoa.setTipo(tipo);

        Setor setor = new Setor(idSetor,nomeSetor);
        pessoa.setSetor(setor);
        this.pessoaRepository.save(pessoa);
        return new ResponseEntity<>(new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getSenha(), pessoa.getSetor().getId(), pessoa.getTipo().getId()), HttpStatus.OK); //usei get para retornar o
    }

    public ResponseEntity<PessoaDTO> findById(long id) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        Setor setor = new Setor();
        Tipo tipo = new Tipo(pessoa.get().getTipo().getId());

        if (pessoa.isEmpty()) {
            throw new RuntimeException("Tipo de usuário não encontrado!");
        } else {
            if(pessoa.get().getSetor() == null) {
                setor.setNome("");
                setor.setId(0);

            }
            return new ResponseEntity<>(new PessoaDTO(pessoa.get().getId(), pessoa.get().getNome(), pessoa.get().getEmail(), pessoa.get().getTelefone(), pessoa.get().getSenha(), tipo.getId(), setor.getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }
    }

    public ResponseEntity<PessoaDTO> updateInfById(long id, Map<String, Object> pessoaDTO) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Tipo de usuário não encontrado!");
        } else {
            Pessoa pessoaNova = new Pessoa();

            pessoaNova.setId(id);
            pessoaNova.setNome((String) pessoaDTO.get("nome"));
            pessoaNova.setEmail((String) pessoaDTO.get("email"));
            pessoaNova.setTelefone((String) pessoaDTO.get("telefone"));

            pessoaNova.setSenha((String) pessoaDTO.get("senha"));
            String nomeSetor = (String) pessoaDTO.get("nomeSetor");

            Setor setor = new Setor();
            setor.setId((int) pessoaDTO.get("idSetor"));
            setor.setNome(nomeSetor);

            pessoaNova.setSetor(setor);

            Tipo tipo = new Tipo();
            tipo.setId(pessoa.get().getId());

            tipo.setNome(pessoa.get().getNome());
            pessoaNova.setTipo(tipo);

            this.pessoaRepository.save(pessoaNova);
            return new ResponseEntity<>(new PessoaDTO(pessoaNova.getId(), pessoaNova.getNome(), pessoaNova.getEmail(), pessoaNova.getTelefone(), pessoaNova.getSenha(), pessoa.get().getTipo().getId(), setor.getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }

    }

    public ResponseEntity<String> deleteById(long id) {
        this.pessoaRepository.deleteById(id);
        return new ResponseEntity<>("Os dados foram deletados com sucesso", HttpStatus.OK);
    }


    public ResponseEntity<List<PessoaDTO>> findAll() {

        List<Pessoa> listOfAll = this.pessoaRepository.findAll();

        List<PessoaDTO> listOfPessoaDTO = new ArrayList<>();
        PessoaDTO pessoaDTO;

        Setor setor = new Setor();

        for (Pessoa pessoa : listOfAll) {
            if(pessoa.getSetor() == null){
              setor.setNome("");
              setor.setId(0);
              pessoa.setSetor(setor);
            }
            pessoaDTO = new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getSenha(), pessoa.getTelefone(), pessoa.getSenha(), pessoa.getTipo().getId(), pessoa.getSetor().getId());
            listOfPessoaDTO.add(pessoaDTO);
        }

        List<String> listUsuario = new ArrayList<>();
        List<String> listTecnico = new ArrayList<>();
        List<String> listAdmin = new ArrayList<>();


        List<Tipo>listTipo = this.tipoRepository.findAll();
        String nomeTipo = " ";
        for (Tipo tipo : listTipo) {
            for (PessoaDTO pessoaDTO1 : listOfPessoaDTO){
                if(tipo.getId() == pessoaDTO1.tipo()){
                    nomeTipo = pessoaDTO1.nome();
                    if(nomeTipo.equals("Usuário")){
                        listUsuario.add(nomeTipo);
                    } else if (nomeTipo.equals("Técnico")) {
                        listTecnico.add(nomeTipo);
                    }else {
                        listAdmin.add(nomeTipo);
                    }
                }
            }
        }

        for(String usuario : listUsuario){
            System.out.println(usuario);
        }

        for(String tecnico : listTecnico){
            System.out.println(tecnico);
        }

        for(String admin : listAdmin){
            System.out.println(admin);
        }

         return new ResponseEntity<>(listOfPessoaDTO, HttpStatus.OK);

    }


}


