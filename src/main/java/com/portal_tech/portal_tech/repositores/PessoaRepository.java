package com.portal_tech.portal_tech.repositores;

import com.portal_tech.portal_tech.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    //Método para fazer a validação de email no banco de dados
    @Query(value = "select * from pessoa where email = :email and senha = :senha", nativeQuery = true)
    public Pessoa verifyLogin(String email, String senha);

    @Query(value = "select * from pessoa where email = :email", nativeQuery = true)
    public Pessoa findEmail(String email);

    @Query(value = "select p.* from pessoa p inner join tipo tecnico on p.id_tipo = tecnico.id where tecnico.nome = :nome", nativeQuery = true)
    public List<Pessoa> findByTipo(@Param("nome") String nome);



}
