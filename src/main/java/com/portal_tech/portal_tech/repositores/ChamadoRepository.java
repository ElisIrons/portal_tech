package com.portal_tech.portal_tech.repositores;

import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.dtos.ChamadoDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    @Query(value = "SELECT * FROM CHAMADO WHERE ID_TECNICO = :id_tecnico", nativeQuery = true)
    public List<Chamado> findById_Tecnico(@Param("id_tecnico") Long id_tecnico);

    @Query(value = "SELECT * FROM CHAMADO WHERE ID_USUARIO = :id_usuario", nativeQuery = true)
    public List<Chamado> findById_Usuario(@Param("id_usuario") Long id_usuario);

    @Query(value = "SELECT * FROM CHAMADO WHERE ID_STATUS = :id_status", nativeQuery = true)
    public List<Chamado> findById_Status(@Param("id_status") Long id_status);

    @Query(value = "SELECT * FROM CHAMADO WHERE ID_PRIORIDADE = :id_prioridade", nativeQuery = true)
    public List<Chamado> findById_Prioridade(@Param("id_prioridade") Long id_prioridade);

    @Query(value = "SELECT A.* " +
            "       FROM CHAMADO A, PESSOA B, SETOR C" +
            "       WHERE A.ID_USUARIO = B.ID" +
            "       AND B.ID_SETOR = C.ID" +
            "       AND C.ID = :id_setor", nativeQuery = true)
    public List<Chamado> findById_Setor(@Param("id_setor") Long id_setor);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE chamado MODIFY COLUMN id_prioridade BIGINT DEFAULT 1; ", nativeQuery = true)
    public void addValorDefaultPrioridade();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE chamado MODIFY COLUMN id_status BIGINT DEFAULT 1; ", nativeQuery = true)
    public void addValorDefaultStatus();

/*    @Query(value = "SELECT COUNT(*) > 0 FROM information_schema.columns WHERE TABLE_NAME = 'chamado' AND column_name = 'id_propriedade' AND column_default IS NOT NULL", nativeQuery = true)
    public boolean isValorDefaultDefinido();*/
}