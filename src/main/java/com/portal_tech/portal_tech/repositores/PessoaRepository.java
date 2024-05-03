package com.portal_tech.portal_tech.repositores;

import com.portal_tech.portal_tech.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
