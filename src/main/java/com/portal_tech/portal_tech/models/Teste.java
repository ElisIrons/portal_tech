package com.portal_tech.portal_tech.models;

public class Teste {

        private Long id;
        private String nome;

        public Teste() { }

        public Teste(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }


