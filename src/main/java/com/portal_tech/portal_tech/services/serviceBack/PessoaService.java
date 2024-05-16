package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.exceptions.ConflictException409;
import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
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

           String email =(String) pessoaDTO.get("email");
            boolean personExists = this.verifyIfPersonAlreadyExistsByEmail(email);
            System.out.println(personExists);
            if(personExists){
                throw new ConflictException409("Pessoa já cadastrada no sistema, o email " + email + " já existe cadastrado");
            }
            Pessoa pessoa = convertDtoToPessoa(pessoaDTO);
            this.pessoaRepository.save(pessoa);
            return new ResponseEntity<>(new PessoaDTO((int) pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getSenha(),(long) pessoa.getSetor().getId(),(long) pessoa.getTipo().getId()), HttpStatus.OK); //usei get para retornar o
        }catch (ConflictException409 e){
            throw new ConflictException409((e.getMessage()));
        }
        catch (Exception e){
            throw  new ExceptionHandler500("Erro ao salvar a pessoa" + e);
        }
    }

    public boolean verifyIfPersonAlreadyExistsByEmail(String email){
        Pessoa pessoa = this.pessoaRepository.findEmail(email);
        System.out.println(pessoa);
        return pessoa.getEmail() != null;
    }

    private static Pessoa convertDtoToPessoa(Map<String, Object> pessoaDTO) {
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
                throw new UnprocessableEntityException422("Não foram encontradas informações do usuário com ID " + id);
            }

                if(pessoa.get().getSetor() == null) {
                    setor.setNome("");
                    setor.setId(0);

                }
                return new ResponseEntity<>(new PessoaDTO((int) pessoa.get().getId(), pessoa.get().getNome(), pessoa.get().getEmail(), pessoa.get().getTelefone(), pessoa.get().getSenha(), tipo.getId(), setor.getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
            }catch (UnprocessableEntityException422 e){
            throw new UnprocessableEntityException422(e.getMessage());
        }catch (Exception e){
            throw new ExceptionHandler500(format("Não foi possível localizar o ID passado, ID = %s",id), e);
        }


    }

    public ResponseEntity<PessoaDTO> updateInfById(long id, Map<String, Object> pessoaDTO) {

        try {
            Pessoa pessoa = this.pessoaRepository.findById(id).orElseThrow(() -> new UnprocessableEntityException422("Pessoa não encontrada"));

            Pessoa pessoaNova = convertPessoatoToDTO(id, pessoaDTO, pessoa);

            this.pessoaRepository.save(pessoaNova);
            return new ResponseEntity<>(new PessoaDTO((int) pessoaNova.getId(), pessoaNova.getNome(), pessoaNova.getEmail(), pessoaNova.getTelefone(), pessoaNova.getSenha(), pessoa.getTipo().getId(), pessoa.getSetor().getId()), HttpStatus.OK); //usei get para retornar o objeto dentro de Optional
        }catch (UnprocessableEntityException422 e){
            throw new UnprocessableEntityException422("Erro ao atualizar os dados da pessoa");
        }
        catch (Exception e){
            throw new ExceptionHandler500("Erro ao atualizar os dados da pessoa");
        }

    }

    private static Pessoa convertPessoatoToDTO(long id, Map<String, Object> pessoaDTO, Pessoa pessoa) {
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
                this.findById(id);
             this.pessoaRepository.deleteById(id);
            return new ResponseEntity<>("Os dados foram deletados com sucesso", HttpStatus.OK);

        }
        catch (Exception e){
            throw new ExceptionHandler500("Erro ao deletar a pessoa ");
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
                pessoaDTO = new PessoaDTO((int) pessoa.getId(), pessoa.getNome(), pessoa.getSenha(), pessoa.getTelefone(), pessoa.getSenha(), pessoa.getTipo().getId(), pessoa.getSetor().getId());
                listOfPessoaDTO.add(pessoaDTO);
            }

//            listas(listOfPessoaDTO);

            return new ResponseEntity<>(listOfPessoaDTO, HttpStatus.OK);
        }catch (Exception e){
            throw new ExceptionHandler500("Erro ao buscar todos as pessoas cadastradas");
        }


    }



//    private void listas(List<PessoaDTO> listOfPessoaDTO) {
//        List<String> listUsuario = new ArrayList<>();
//        List<String> listTecnico = new ArrayList<>();
//        List<String> listAdmin = new ArrayList<>();
//
//
//        List<Tipo>listTipo = this.tipoRepository.findAll();
//        String nomeTipo = " ";
//        for (Tipo tipo : listTipo) {
//            for (PessoaDTO pessoaDTO1 : listOfPessoaDTO){
//                if(tipo.getId() == pessoaDTO1.tipo()){
//                    nomeTipo = tipo.getNome();
//                    if(nomeTipo.equals("Usuário")){
//                        listUsuario.add(nomeTipo);
//                    } else if (nomeTipo.equals("Técnico")) {
//                        listTecnico.add(nomeTipo);
//                    }else {
//                        listAdmin.add(nomeTipo);
//                    }
//                }
//            }
//        }
//
//        for(String usuario : listUsuario){
//            System.out.println("lista usuario:  " + usuario);
//        }
//
//        for(String tecnico : listTecnico){
//            System.out.println("lista técnico " + tecnico);
//        }
//
//        for(String admin : listAdmin){
//            System.out.println("lista admin" + admin);
//        }
//    }


}


