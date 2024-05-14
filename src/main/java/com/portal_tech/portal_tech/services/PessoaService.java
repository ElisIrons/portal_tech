package com.portal_tech.portal_tech.services;

import com.portal_tech.portal_tech.exceptions.CustomExceptionHandler;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException;
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

import static java.lang.String.format;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TipoRepository tipoRepository;

    public ResponseEntity<PessoaDTO> register(Map<String, Object> pessoaDTO) {
        try {
            Pessoa pessoa = convertToDTO(pessoaDTO);

            this.pessoaRepository.save(pessoa);
            return new ResponseEntity<>(new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getSenha(), pessoa.getSetor().getId(), pessoa.getTipo().getId()), HttpStatus.OK); //usei get para retornar o
        }catch (Exception e){
            throw  new CustomExceptionHandler("Erro ao salvar a pessoa" + e);
        }
    }

    private static Pessoa convertToDTO(Map<String, Object> pessoaDTO) {
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
        return pessoa;
    }

    public ResponseEntity<PessoaDTO> findById(long id) {
    try{
            Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
            Setor setor = new Setor();
            Tipo tipo = new Tipo(pessoa.get().getTipo().getId());

            if (pessoa.isEmpty()) {
                throw new UnprocessableEntityException("Usuário não encontrado!");
            }

                if(pessoa.get().getSetor() == null) {
                    setor.setNome("");
                    setor.setId(0);

                }
                return new ResponseEntity<>(new PessoaDTO(pessoa.get().getId(), pessoa.get().getNome(), pessoa.get().getEmail(), pessoa.get().getTelefone(), pessoa.get().getSenha(), tipo.getId(), setor.getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
            }catch (UnprocessableEntityException e){
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e){
            throw new CustomExceptionHandler(format("Não foi possível localizar o ID passado, ID = %s",id), e);
        }


    }

    public ResponseEntity<PessoaDTO> updateInfById(long id, Map<String, Object> pessoaDTO) {

        try {
            Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow(() -> new UnprocessableEntityException("Pessoa não encontrada"));

            Pessoa pessoaNova = converPessoatoToDTO(id, pessoaDTO, pessoa);

            this.pessoaRepository.save(pessoaNova);
            return new ResponseEntity<>(new PessoaDTO(pessoaNova.getId(), pessoaNova.getNome(), pessoaNova.getEmail(), pessoaNova.getTelefone(), pessoaNova.getSenha(), pessoa.getTipo().getId(), pessoa.getSetor().getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }catch (Exception e){
            throw new CustomExceptionHandler("Erro ao atualizar os dados da pessoa");
        }

    }

    private static Pessoa converPessoatoToDTO(long id, Map<String, Object> pessoaDTO, Pessoa pessoa) {
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
        tipo.setId(pessoa.getId());
        tipo.setNome(pessoa.getTipo().getNome());
        pessoaNova.setTipo(tipo);
        return pessoaNova;
    }

    public ResponseEntity<String> deleteById(long id) {
        try{

        if(this.findById(id).equals(new CustomExceptionHandler("Usuário não encontrado!"))){
            throw new UnprocessableEntityException("Não foi possível deletar o produto com id" + id);
        }else{
            this.pessoaRepository.deleteById(id);
            return new ResponseEntity<>("Os dados foram deletados com sucesso", HttpStatus.OK);
        }
        }catch (Exception e){
            throw new CustomExceptionHandler("Eroo ao deletar a pessoa");
        }
    }

//Fazer estas listas separadas se tiver tempo
    public ResponseEntity<List<PessoaDTO>> findAll() {
        try {
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

            listas(listOfPessoaDTO);

            return new ResponseEntity<>(listOfPessoaDTO, HttpStatus.OK);
        }catch (Exception e){
            throw new CustomExceptionHandler("Erro ao buscar todos as pessoas cadastradas");
        }


    }

    private void listas(List<PessoaDTO> listOfPessoaDTO) {
        List<String> listUsuario = new ArrayList<>();
        List<String> listTecnico = new ArrayList<>();
        List<String> listAdmin = new ArrayList<>();


        List<Tipo>listTipo = this.tipoRepository.findAll();
        String nomeTipo = " ";
        for (Tipo tipo : listTipo) {
            for (PessoaDTO pessoaDTO1 : listOfPessoaDTO){
                if(tipo.getId() == pessoaDTO1.tipo()){
                    nomeTipo = tipo.getNome();
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
            System.out.println("lista usuario:  " + usuario);
        }

        for(String tecnico : listTecnico){
            System.out.println("lista técnico " + tecnico);
        }

        for(String admin : listAdmin){
            System.out.println("lista admin" + admin);
        }
    }


}


