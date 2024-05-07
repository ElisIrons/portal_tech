package com.portal_tech.portal_tech.repositores;

import com.portal_tech.portal_tech.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
