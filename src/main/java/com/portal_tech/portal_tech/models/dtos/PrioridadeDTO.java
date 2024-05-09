package com.portal_tech.portal_tech.models.dtos;


import com.portal_tech.portal_tech.models.Prioridade;


public class PrioridadeDTO {

        private Long id;

        private String nome;



        public PrioridadeDTO() {

        }

        public PrioridadeDTO(Long id) {
            this.id = id;
        }


        public PrioridadeDTO(Prioridade prioridade) {
            this.id = prioridade.getId();
            this.nome = prioridade.getNome();
        }


        public static Prioridade convert(PrioridadeDTO prioridadeDTO){
            Prioridade prioridade = new Prioridade();
            prioridade.setId(prioridadeDTO.getId());
            prioridade.setNome(prioridadeDTO.getNome());
            return prioridade;
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

