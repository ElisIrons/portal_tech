package com.portal_tech.portal_tech.services.serviceBack;

import com.portal_tech.portal_tech.exceptions.ExceptionHandler500;
import com.portal_tech.portal_tech.exceptions.UnprocessableEntityException422;
import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.dtos.SetorDTO;
import com.portal_tech.portal_tech.repositores.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public ResponseEntity<String> save() {
        List<SetorDTO> setor = new ArrayList<>();

        SetorDTO setor1 = new SetorDTO();
        setor1.setNome("Financeiro");
        setorRepository.save(SetorDTO.convert(setor1));

        return new ResponseEntity<>("arrumar este método", HttpStatus.CREATED);
    }

    public ResponseEntity<SetorDTO> findById(long id) {
        try {
            Optional<Setor> resultado = this.setorRepository.findById(id);
            if (resultado.isEmpty()) {
                throw new UnprocessableEntityException422("Setor não encontrado!");
            } else {
                return new ResponseEntity<>(new SetorDTO(resultado.get()), HttpStatus.OK);
            }
        } catch (UnprocessableEntityException422 e) {
            throw new UnprocessableEntityException422(e.getMessage());
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro no servidor ao tentar encontrar o ID=" + id);
        }
    }

    public ResponseEntity<List<SetorDTO>> findAll() {
        try {
            List<Setor> setores = setorRepository.findAll();
            if (setores.isEmpty()) {
                throw new UnprocessableEntityException422("Não há setores cadastrados!");
            } else {
                List<SetorDTO> listSetorDTO = setores.stream().map(SetorDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(listSetorDTO, HttpStatus.OK);
            }
        } catch (UnprocessableEntityException422 e) {
            throw new UnprocessableEntityException422(e.getMessage());
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro no servidor");
        }
    }

    public ResponseEntity<SetorDTO> updateById(long id, SetorDTO setorDTO) {
        this.findById(id);
        setorDTO.setId(id);
        this.setorRepository.save(new Setor(setorDTO.getId(), setorDTO.getNome()));
        return new ResponseEntity<>(setorDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteById(long id) {
        try {
            this.setorRepository.deleteById(id);
            return new ResponseEntity<>("setor deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new ExceptionHandler500("Erro no servidor");
        }
    }

}