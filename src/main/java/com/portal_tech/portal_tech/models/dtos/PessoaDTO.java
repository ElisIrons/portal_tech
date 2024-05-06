package com.portal_tech.portal_tech.models.dtos;

import com.portal_tech.portal_tech.models.Setor;
import com.portal_tech.portal_tech.models.Tipo;
import com.portal_tech.portal_tech.models.TiposEnums;

public record PessoaDTO(long id, String nome, String email, String senha, String telefone, Long idtipo,
                        int idsetor) {

    public String nome() {
        return this.nome;
    }

    public String email() {
        return this.email;
    }

    public String senha() {
        return this.senha;
    }

    public String telefone() {
        return this.telefone;
    }

    public Long idTipo() {
        return this.idtipo;
    }

    public long idSetor() {
        return this.idsetor;
    }


}
