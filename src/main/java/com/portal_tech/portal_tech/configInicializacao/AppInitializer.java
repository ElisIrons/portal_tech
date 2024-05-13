package com.portal_tech.portal_tech.configInicializacao;

import com.portal_tech.portal_tech.repositores.ChamadoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer {
    @Autowired
    private ChamadoRepository chamadoRepository;

/*    @PostConstruct
    public void init(){
        boolean valorDefaultDefinido = chamadoRepository.isValorDefaultDefinido();

        if (!valorDefaultDefinido) {
            chamadoRepository.addValorDefaultPrioridade();
        }

        chamadoRepository.addValorDefaultStatus();
    }

 */
}
