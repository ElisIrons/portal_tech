package com.portal_tech.portal_tech.models.dtos;

public record PessoaDTO(Integer id, String nome, String email, String senha, String telefone, long tipo,
                        long setor) {





    public PessoaDTO(Integer id, String nome, String email, String senha, String telefone, long tipo, long setor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;
        this.setor = setor;
    }


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

        public long tipo() {
            return this.tipo;

        }

        public long setor() {
            return this.setor;

        }
    }



