package com.portal_tech.portal_tech.models.dtos;
import com.portal_tech.portal_tech.models.Chamado;
import com.portal_tech.portal_tech.models.Pessoa;
import com.portal_tech.portal_tech.models.Prioridade;
import com.portal_tech.portal_tech.models.Status;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.Timestamp;

public class ChamadoDTO {
    private Long id;

    private String descricao;

    private Timestamp dt_abertura;

    private Timestamp dt_inicio;

    private Timestamp dt_fim;

    private Pessoa id_tecnico;

    private Pessoa id_usuario;

    private Prioridade id_prioridade;

    private Status id_status;

    public ChamadoDTO() {
    }

    public ChamadoDTO(Chamado chamado) { //esse construtor transforma o chamado em chamadoDTO
        this.id = chamado.getId();
        this.descricao = chamado.getDescricao();
        this.dt_abertura = chamado.getDt_abertura();
        this.dt_inicio = chamado.getDt_inicio();
        this.dt_fim = chamado.getDt_fim();
        this.id_tecnico = chamado.getIdTecnico();
        this.id_usuario = chamado.getIdUsuario();
        this.id_prioridade = chamado.getIdPrioridade();
        this.id_status = chamado.getIdStatus();
    }

    public static Chamado convert(ChamadoDTO chamadoDTO){ //converterá o chamadoDTO em chamado - inverso do método anterior
        Chamado chamado = new Chamado();
        chamado.setId(chamadoDTO.getId());
        chamado.setDescricao(chamadoDTO.getDescricao());
        chamado.setDt_abertura(chamado.getDt_abertura());
        chamado.setDt_inicio(chamado.getDt_inicio());
        chamado.setDt_fim(chamado.getDt_fim());
        chamado.setIdTecnico(chamadoDTO.getId_tecnico());
        //chamado.setIdTecnico(new Pessoa(chamadoDTO.getId_tecnico()));
        chamado.setIdUsuario(chamadoDTO.getId_usuario());
        //chamado.setIdUsuario(new Pessoa(chamadoDTO.getId_usuario()));
        chamado.setIdPrioridade(chamadoDTO.getId_prioridade());
        //chamado.setIdPrioridade(new Prioridade(chamadoDTO.getId_prioridade()));
        chamado.setIdStatus(chamadoDTO.getId_status());
        //chamado.setIdStatus(new Status(chamadoDTO.getId_status()));
        return chamado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(Timestamp dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public Timestamp getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Timestamp dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Timestamp getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Timestamp dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Pessoa getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(Pessoa id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public Pessoa getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Pessoa id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Prioridade getId_prioridade() {
        return id_prioridade;
    }

    public void setId_prioridade(Prioridade id_prioridade) {
        this.id_prioridade = id_prioridade;
    }

    public Status getId_status() {
        return id_status;
    }

    public void setId_status(Status id_status) {
        this.id_status = id_status;
    }
}


