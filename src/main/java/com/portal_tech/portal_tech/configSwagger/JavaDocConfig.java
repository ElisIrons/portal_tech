package com.portal_tech.portal_tech.configSwagger;

import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class JavaDocConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Portal Tech")
                        .description("Rest API do sistema de chamados técnicos em informática ")
                )
                .tags(
                        Arrays.asList(
                                new Tag().name("Pessoa").description("Gerenciamento de dados da pessoa a ser cadastrada ou cadastrada. A pessoa pode ser classifcada em técnico, administrador ou usuário"),
                                new Tag().name("Tipo").description("Técnico/Usuário/Aministrador"),
                                new Tag().name("Prioridade").description("Alta/média/baixa"),
                                new Tag().name("Chamado").description("Gerenciamento dos dados das requisições que a aplicação recebe"),
                                new Tag().name("Status").description("Aberto/Em andamento/Finalizado"),
                                new Tag().name("Setor").description("Informática/RH/Marketing"),
                                new Tag().name("Autenticação").description("")
                        )
                );
    }


}
