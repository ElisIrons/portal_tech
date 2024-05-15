package com.portal_tech.portal_tech.repositores;

import com.portal_tech.portal_tech.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    //Método para fazer a validação de email no banco de dados
    @Query(value = "select * from pessoa where email = :email and senha = :senha", nativeQuery = true)
    public Pessoa verifyLogin(String email, String senha);

    @Query(value = "select * from pessoa where email = :email", nativeQuery = true)
    public Pessoa findEmail(String email);



}
